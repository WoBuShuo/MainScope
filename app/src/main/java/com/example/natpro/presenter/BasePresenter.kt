package com.example.natpro.presenter

import android.util.Log
import com.example.natpro.bean.BaseResult
import com.example.natpro.bean.HotKeyBean
import com.example.natpro.bean.RankBean
import com.example.natpro.server.HttpServer
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.*
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type
import java.net.ConnectException

open class BasePresenter {
    private val scope = MainScope()

    fun <T> retrofit(clazz: Class<T>, dsl: RetrofitCoroutineDSL<T>.() -> Unit) {
        //在主线程中开启协程
        scope.launch(Dispatchers.Main) {
            val coroutine = RetrofitCoroutineDSL<T>().apply(dsl)
            coroutine.api?.let { call ->
                //async 并发执行 在IO线程中
                val deferred = async(Dispatchers.IO) {
                    try {
                        call.execute() //已经在io线程中了，所以调用Retrofit的同步方法
                    } catch (e: ConnectException) {
                        coroutine.onFail?.invoke("网络连接出错", -1)
                        null
                    } catch (e: IOException) {
                        coroutine.onFail?.invoke("未知网络错误", -1)
                        null
                    }
                }
                //当协程取消的时候，取消网络请求
                deferred.invokeOnCompletion {
                    if (deferred.isCancelled) {
                        call.cancel()
                        coroutine.clean()
                    }
                }
                //await 等待异步执行的结果
                val response = deferred.await()
                if (response == null) {
                    coroutine.onFail?.invoke("返回为空", -1)
                } else {
                    val t = response.body()?.string()
                    try {
                        //Gson解析泛型报错的处理
                        val type = ParameterizedTypeImpl(BaseResult<T>().javaClass, arrayOf<Class<*>>(clazz))
                        val data = Gson().fromJson<BaseResult<T>>(t, type)

                        coroutine.onSuccess?.invoke(data)
                    }catch (e : JsonSyntaxException){
                        coroutine.onFail?.invoke("解析数据错误",-1)
                    }

                }
                coroutine.onComplete?.invoke()
            }
        }
    }


    fun <T> retrofitList(clazz: Class<T>, dsl: RetrofitCoroutineDSL<T>.() -> Unit) {
        //在主线程中开启协程
        MainScope().launch(Dispatchers.Main) {
            val coroutine = RetrofitCoroutineDSL<T>().apply(dsl)
            coroutine.api?.let { call ->
                //async 并发执行 在IO线程中
                val deferred = async(Dispatchers.IO) {
                    try {
                        call.execute() //已经在io线程中了，所以调用Retrofit的同步方法
                    } catch (e: ConnectException) {
                        coroutine.onFail?.invoke("网络连接出错", -1)
                        null
                    } catch (e: IOException) {
                        coroutine.onFail?.invoke("未知网络错误", -1)
                        null
                    }
                }
                //当协程取消的时候，取消网络请求
                deferred.invokeOnCompletion {
                    if (deferred.isCancelled) {
                        call.cancel()
                        coroutine.clean()
                    }
                }
                //await 等待异步执行的结果
                val response = deferred.await()
                if (response == null) {
                    coroutine.onFail?.invoke("返回为空", -1)
                } else {
                    response.let {
                        val t = response.body()?.string()
                        //Gson解析泛型报错的处理
                        val listType =
                            ParameterizedTypeImpl(ArrayList::class.java, arrayOf<Class<*>>(clazz))
                        val type = ParameterizedTypeImpl(
                            BaseResult<T>().javaClass,
                            arrayOf<Type>(listType)
                        )
                        val data = Gson().fromJson<BaseResult<List<T>>>(t, type)

                        coroutine.onSuccessList?.invoke(data)
                    }
                }
                coroutine.onComplete?.invoke()
            }
        }
    }

    fun getRankData() {
        retrofit(RankBean::class.java) {
            api = Retrofit.Builder().baseUrl("https://www.wanandroid.com/").build()
                .create(HttpServer::class.java).rankData()
            onSuccess {
                val bean = it.data as RankBean
                Log.e("-----", "data: " + bean.total)
            }
            //失败回调
            onFail { msg, errorCode ->

            }
            //完成回调
            onComplete {

            }
        }
    }

    fun getHotkeyData() {
        retrofitList(HotKeyBean::class.java) {
            api = Retrofit.Builder().baseUrl("https://www.wanandroid.com/").build()
                .create(HttpServer::class.java).hotKeyData()
            onSuccessList {
                val bean = it.data as List<HotKeyBean>
                Log.e("-----", "data: " + bean.get(0).name)
            }
        }
    }

    fun destory(){
        scope.cancel()
    }

}
