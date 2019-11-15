package com.example.natpro

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.view.ViewTreeObserver
import com.example.natpro.presenter.BasePresenter
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : Activity() {


    var presenter=BasePresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()
        presenter.getRankData()
        presenter.getHotkeyData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destory()
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


//    fun obser() {
//
//
//
//        MainScope().launch { }
//        GlobalScope.launch {
//
//        }
//
//        val create = Observable.create<String> {
//
//            it.onNext("");
//            it.onComplete()
//
//        }.subscribeOn(Schedulers.io())// 指定被观察者发生在 IO 线程
//            .observeOn(AndroidSchedulers.mainThread())
//            .flatMap(object : Function<String, ObservableSource<String>> {
//                override fun apply(t: String): ObservableSource<String> {
//                    return Observable.just(t);
//                }
//            })
//            .subscribe(object : Observer<String> {
//                override fun onComplete() {
//
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                }
//
//                override fun onNext(t: String) {
//                }
//
//                override fun onError(e: Throwable) {
//
//                }
//            })
//
//
//    }


}
