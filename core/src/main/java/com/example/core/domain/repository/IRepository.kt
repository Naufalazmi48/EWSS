package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Diagnosa
import com.example.core.domain.model.Login
import com.example.core.presentation.model.DiagnosaForm
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun login(email: String, password: String): Flow<Resource<Login>>
    suspend fun diagnosa(diagnosaForm: DiagnosaForm): Flow<Resource<Diagnosa>>
}