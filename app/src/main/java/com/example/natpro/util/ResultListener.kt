package com.example.natpro.util

import com.example.natpro.bean.BaseResult
import okhttp3.ResponseBody
import retrofit2.Call

class ResultListener<T> {

    var api: (Call<ResponseBody>)? = null
    internal var onSuccess: ((BaseResult<T>) -> Unit)? = null
        private set

    internal var onFail: ((msg: String, errorCode: Int) -> Unit)? = null
        private set

    internal var onComplete: (() -> Unit)? = null
        private set

    internal var onSuccessList: ((BaseResult<List<T>>) -> Unit)? = null
        private set

    /**
     * 获取数据成功,json数据是数组的情况
     * @param block (T) -> Unit
     */
    fun onSuccessList(block: (BaseResult<List<T>>) -> Unit) {
        this.onSuccessList = block
    }

    /**
     * 获取数据成功
     * @param block (T) -> Unit
     */
    fun onSuccess(block: (BaseResult<T>) -> Unit) {
        this.onSuccess = block
    }

    /**
     * 获取数据失败
     * @param block (msg: String, errorCode: Int) -> Unit
     */
    fun onFail(block: (msg: String, errorCode: Int) -> Unit) {
        this.onFail = block
    }

    /**
     * 访问完成
     * @param block () -> Unit
     */
    fun onComplete(block: () -> Unit) {
        this.onComplete = block
    }

    internal fun clean() {
        onSuccess = null
        onComplete = null
        onFail = null
    }
}