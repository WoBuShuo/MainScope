package com.example.myapplication.coroutine_mvp

import com.example.natpro.bean.BaseResult
import com.example.natpro.util.ParameterizedTypeImpl
import com.example.natpro.util.ResultListener
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.*
import java.io.IOException
import java.net.ConnectException

/**
 * Created by sos on 2017/11/1.
 */
open class BasePresenter<V> {
    var view: V? = null
        private set

    fun attachView(view: V?) {
        this.view = view
    }

    fun detachView() {
        scope.cancel()
        view = null
    }

    private val scope = MainScope()

    fun <T> retrofit(clazz: Class<T>, dsl: ResultListener<T>.() -> Unit) {
        //在主线程中开启协程
        scope.launch(Dispatchers.Main) {
            val listener = ResultListener<T>().apply(dsl)
            listener.api?.let { call ->
                //async 并发执行 在IO线程中
                val deferred = async(Dispatchers.IO) {
                    try {
                        call.execute() //已经在io线程中了，所以调用Retrofit的同步方法
                    } catch (e: ConnectException) {
                        listener.onFail?.invoke("网络连接出错", -1)
                        null
                    } catch (e: IOException) {
                        listener.onFail?.invoke("未知网络错误", -1)
                        null
                    }
                }
                //当协程取消的时候，取消网络请求
                deferred.invokeOnCompletion {
                    if (deferred.isCancelled) {
                        call.cancel()
                        listener.clean()
                    }
                }
                //await 等待异步执行的结果
                val response = deferred.await()
                if (response == null) {
                    listener.onFail?.invoke("返回为空", -1)
                } else {
                    val t = response.body()?.string()
                    try {
                        //Gson解析泛型报错的处理
                        val type = ParameterizedTypeImpl(
                            BaseResult<T>().javaClass,
                            arrayOf<Class<*>>(clazz)
                        )
                        val data = Gson().fromJson<BaseResult<T>>(t, type)

                        listener.onSuccess?.invoke(data)
                    }catch (e : JsonSyntaxException){
                        listener.onFail?.invoke("解析数据错误",-1)
                    }

                }
                listener.onComplete?.invoke()
            }
        }
    }

}