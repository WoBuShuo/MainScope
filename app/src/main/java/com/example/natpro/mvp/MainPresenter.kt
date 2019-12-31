package com.example.natpro.mvp

import android.util.Log
import com.example.myapplication.coroutine_mvp.BasePresenter
import com.example.natpro.bean.HotKeyBean
import com.example.natpro.bean.RankBean
import com.example.natpro.server.HttpServer
import retrofit2.Retrofit

class MainPresenter :BasePresenter<MainView>(){

    fun getRankData(){
        retrofit(RankBean::class.java) {
            api = Retrofit.Builder().baseUrl("https://www.wanandroid.com/").build()
                .create(HttpServer::class.java).rankData()
            onSuccess {
                val bean = it.data as RankBean
                Log.e("MainPresenter", "请求排行榜成功: " + bean.total)
                view?.getRankSucceed(bean)
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
        retrofit(HotKeyBean::class.java) {
            api = Retrofit.Builder().baseUrl("https://www.wanandroid.com/").build()
                .create(HttpServer::class.java).hotKeyData()
            onSuccessList {
                val bean = it.data as List<HotKeyBean>
                Log.e("-----", "data: " + bean.get(0).name)
            }
        }
    }

}