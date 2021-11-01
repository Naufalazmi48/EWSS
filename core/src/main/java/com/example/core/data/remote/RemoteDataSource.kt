package com.example.core.data.remote

import android.util.Log
import com.example.core.data.preferences.UserPreferences
import com.example.core.data.remote.network.ApiResponse
import com.example.core.data.remote.network.ApiService
import com.example.core.data.remote.response.*
import com.example.core.presentation.model.DiagnosaForm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService, private val prefs: UserPreferences) {
    suspend fun login(email: String, password: String): Flow<ApiResponse<DataLogin>> =
        flow {
            try {
                val result = apiService.login(email, password)
                if (result.data == null) emit(ApiResponse.Empty)
                else {
                    if (result.token != null) prefs.saveApiKey(result.token)
                    emit(ApiResponse.Success(result.data))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(javaClass.name, e.toString())
            }
        }

    suspend fun diagnosa(diagnosaForm: DiagnosaForm): Flow<ApiResponse<DataDiagnosa>> =
        flow {
            try {
                val authToken: String? = prefs.getApiKey().first()

                if (authToken.isNullOrEmpty()) {
                    emit(ApiResponse.Error("You need auth token to do this request"))
                    return@flow
                }

                val result = apiService.diagnosa(
                    authToken = "Bearer $authToken",
                    fullname = diagnosaForm.fullname,
                    address = diagnosaForm.address,
                    age = diagnosaForm.age,
                    kesadaran = diagnosaForm.kesadaran,
                    pernafasan = diagnosaForm.pernafasan,
                    denyutNadi = diagnosaForm.denyutNadi,
                    tekananDarah = diagnosaForm.tekananDarah,
                    suhu = diagnosaForm.suhu
                )
                if (result.data == null) emit(ApiResponse.Empty)
                else {
                    emit(ApiResponse.Success(result.data))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(javaClass.name, e.toString())
            }
        }

    suspend fun historyDiagnosa(): Flow<ApiResponse<List<DataDiagnosa>>> =
        flow {
            try {
                val authToken: String? = prefs.getApiKey().first()

                if (authToken.isNullOrEmpty()) {
                    emit(ApiResponse.Error("You need auth token to do this request"))
                    return@flow
                }

                val result = apiService.historyDiagnosa("Bearer $authToken")
                if (result.data.isNullOrEmpty()) emit(ApiResponse.Empty)
                else {
                    val list = arrayListOf<DataDiagnosa>()
                    result.data.forEach {
                        if (it != null) list.add(it)
                    }
                    emit(ApiResponse.Success(list))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(javaClass.name, e.toString())
            }
        }
}