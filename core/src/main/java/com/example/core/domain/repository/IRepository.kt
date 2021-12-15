package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.*
import com.example.core.presentation.model.DiagnosaForm
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun login(email: String, password: String): Flow<Resource<Login>>
    suspend fun register(register: Register): Flow<Resource<Boolean>>
    suspend fun diagnosa(diagnosaForm: DiagnosaForm): Flow<Resource<Diagnosa>>
    suspend fun getHistoryDiagnosa(): Flow<Resource<List<HistoryDiagnosa>>>
    suspend fun getStatisticPatient(): Flow<Resource<StatisticPatient>>
}