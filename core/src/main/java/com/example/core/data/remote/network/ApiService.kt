package com.example.core.data.remote.network

import com.example.core.data.remote.response.DiagnosaResponse
import com.example.core.data.remote.response.HistoryDiagnosaResponse
import com.example.core.data.remote.response.LoginResponse
import retrofit2.http.*

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
        @Field("pernafasan") pernafasan: Double,
        @Field("denyut_nadi") denyutNadi: Double,
        @Field("tekanan_darah") tekananDarah: Double,
        @Field("suhu") suhu: Double,
    ): DiagnosaResponse

    @GET("diagnosa/histories")
    suspend fun historyDiagnosa(
        @Header("Authorization") authToken: String,
    ): HistoryDiagnosaResponse
}
