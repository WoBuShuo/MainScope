package com.example.natpro.bean

class BaseResult<T> {

    var errorCode: Int = 0

    var errorMsg: String = ""

    var data: T? = null

}
