package com.example.core.data.remote.network

import com.example.core.data.remote.response.DiagnosaResponse
import com.example.core.data.remote.response.HistoryDiagnosaResponse
import com.example.core.data.remote.response.LoginResponse
import com.example.core.data.remote.response.RegisterResponse
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("nama") name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("country") country: String,
        @Field("city") city: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("diagnosa/imperative")
    suspend fun diagnosa(
        @Header("Authorization") authToken: String,
        @Field("nama") fullname: String,
        @Field("alamat") address: String,
        @Field("usia") age: Int,
        @Field("tingkat_kesadaran") kesadaran: String,
        @Field("pernafasan") pernafasan: Int,
        @Field("denyut_nadi") denyutNadi: Int,
        @Field("tekanan_darah") tekananDarah: Int,
        @Field("suhu") suhu: Double,
    ): DiagnosaResponse

    @GET("diagnosa/histories")
    suspend fun historyDiagnosa(
        @Header("Authorization") authToken: String,
    ): HistoryDiagnosaResponse
}
