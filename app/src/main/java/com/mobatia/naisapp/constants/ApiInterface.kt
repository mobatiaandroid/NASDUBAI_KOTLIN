package com.mobatia.naisapp.constants

import com.mobatia.naisapp.fragment.primary.model.Primarydetailsresponse
import com.mobatia.naisapp.fragment.primary.model.Primaryresponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {

    /*************LOGIN****************/
    @POST("api/v1/parent/auth/login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("devicetype") devicetype: Int,
        @Field("deviceid") fcmid: String,
        @Field("device_identifier") deviceid: String
    ): Call<ResponseBody>

    /*************FORGOT PASSWORD****************/
    @POST("api/v1/parent/auth/forgotpassword")
    @FormUrlEncoded
    fun forgotpassword(
        @Field("email") email: String
    ): Call<ResponseBody>

    /*************FORGOT PASSWORD****************/
    @POST("api/v1/parent/auth/register")
    @FormUrlEncoded
    fun registeruser(
        @Field("email") email: String
    ): Call<ResponseBody>

    /*************PRIMARY****************/
    @GET("api/v1/parent/departmentprimary")
    fun primarylist(): Call<Primaryresponse>

    /*************PRIMARY DETAIL****************/
    @POST("api/v1/parent/departmentprimary/detail")
    @FormUrlEncoded
    fun primarydetails(
        @Field("departmentprimary_id") departmentprimary_id: Int,
        @Field("page_number") page_number: Int
    ): Call<Primarydetailsresponse>
}