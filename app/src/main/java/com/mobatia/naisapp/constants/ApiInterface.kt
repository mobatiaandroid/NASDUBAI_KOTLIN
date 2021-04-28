package com.mobatia.naisapp.constants

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    /*************LOGIN****************/
    @POST("api/v1/login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("devicetype") devicetype: Int,
        @Field("deviceid") fcmid: String,
        @Field("device_identifier") deviceid: String
    ): Call<ResponseBody>
}