package com.mengbao.baselibrary.mvp

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutId = setLayoutResID()
        if (layoutId != 0) {
            setContentView(layoutId)
        }
        initView()
    }

    /**
     * 初始化数据
     */
    protected abstract fun initView()

    /**
     * 绑定布局
     *
     * @return 布局id
     */
    protected abstract fun setLayoutResID(): Int



}
