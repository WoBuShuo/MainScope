package com.mengbao.baselibrary.mvp

/**
 * Created by sos on 2017/11/2.
 */
import android.os.Bundle
import com.example.myapplication.coroutine_mvp.BasePresenter


abstract class MPBaseActivity<V, P : BasePresenter<V>> : BaseActivity() {
    var presenter: P? = null
        private set
     var mView:V? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        if (presenter == null) {
            presenter = createPresenter()
        }
        if (mView == null) {
            mView = createView()
        }
        if (presenter == null) {
            throw NullPointerException("p不能为空")
        }
        if (mView == null) {
            throw NullPointerException("v不能为空")
        }
        //p层绑定v层
        presenter?.attachView(mView)
        super.onCreate(savedInstanceState)


    }


    //可能会有很多p和v层，这个由子类实现创建v跟p
    abstract fun createPresenter(): P

    abstract fun createView(): V

    //解绑当activity销毁就不再更新ui
    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }
}
