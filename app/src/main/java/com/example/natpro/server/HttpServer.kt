package com.example.natpro.server

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

internal interface HttpServer{

    @GET("coin/rank/1/json")
    fun rankData(): Call<ResponseBody>

    @GET("hotkey/json")
    fun hotKeyData(): Call<ResponseBody>

}
