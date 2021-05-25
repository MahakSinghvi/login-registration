package com.ps.loginregistration.api_call

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface APIInter {

    @GET("api/languages")
    fun languages(): Call<ResponseBody?>?

    //todo using while login
    //http://192.168.0.131:8001/api/send_otp
    //user_mobile
    // language_id
    @FormUrlEncoded
    @POST("api/send_otp")
    fun send_otp(@FieldMap hm: HashMap<String, String>): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("api/states")
    fun states(): Call<ResponseBody?>?


    //todo for verification of OTP
    //http://192.168.0.131:8001/api/verify_otp
    //"user_otp_id
    //otp_code"
    @FormUrlEncoded
    @POST("api/verify_otp")
    fun verify_otp(
        @Header("X-localization") language: String?,
        @FieldMap hm: HashMap<String, String>
    ): Call<ResponseBody?>?



}