package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Login
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun login(email: String, password: String): Flow<Resource<Login>>
}