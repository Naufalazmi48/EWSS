package com.example.core.data.remote

import android.util.Log
import com.example.core.data.preferences.UserPreferences
import com.example.core.data.remote.network.ApiResponse
import com.example.core.data.remote.network.ApiService
import com.example.core.data.remote.response.DataLogin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService, private val prefs: UserPreferences) {
    suspend fun login(email: String, password: String):Flow<ApiResponse<DataLogin>> =
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
}