package com.example.core.data.remote.network

import com.example.core.data.remote.response.DiagnosaResponse
import com.example.core.data.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("diagnosa")
    suspend fun diagnosa(
        @Header("Authorization") authToken: String,
        @Field("nama") fullname: String,
        @Field("alamat") address: String,
        @Field("usia") age: Int,
        @Field("tingkat_kesadaran") kesadaran: String,
        @Field("pernafasan") pernafasan: Int,
        @Field("denyut_nadi") denyutNadi: Int,
        @Field("tekanan_darah") tekananDarah: Int,
        @Field("suhu") suhu: Int,
    ): DiagnosaResponse
}
