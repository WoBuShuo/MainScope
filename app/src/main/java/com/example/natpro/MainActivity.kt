package com.example.natpro

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.view.ViewTreeObserver
import com.example.natpro.bean.RankBean
import com.example.natpro.mvp.MainPresenter
import com.example.natpro.mvp.MainView
import com.mengbao.baselibrary.mvp.MPBaseActivity
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : MPBaseActivity<MainView, MainPresenter>(), MainView  {

    override fun initView() {
        sample_text.text = stringFromJNI()

        //开始请求
        presenter?.getRankData()
    }

    override fun setLayoutResID(): Int {
        return  R.layout.activity_main
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun createView(): MainView {
        return this
    }

    //请求成功回调
    override fun getRankSucceed(rankBean: RankBean) {
        sample_text.text = rankBean.pageCount.toString()
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }


}
