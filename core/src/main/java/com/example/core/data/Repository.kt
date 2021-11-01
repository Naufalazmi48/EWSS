package com.example.core.data

import com.example.core.data.remote.RemoteDataSource
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.Diagnosa
import com.example.core.domain.model.HistoryDiagnosa
import com.example.core.domain.model.Login
import com.example.core.domain.repository.IRepository
import com.example.core.presentation.model.DiagnosaForm
import com.example.core.utils.Mapper.mapDiagnosaResponseToDomain
import com.example.core.utils.Mapper.mapHistoryDiagnosaResponseToDomain
import com.example.core.utils.Mapper.mapLoginResponseToDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(private val remoteDataSource: RemoteDataSource) : IRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<Login>> = flow {
        emit(Resource.Loading())
        remoteDataSource.login(email, password).collect {
            when (it) {
                is ApiResponse.Empty -> emit(Resource.Error<Login>(message = "Failed to Login"))
                is ApiResponse.Error -> emit(Resource.Error<Login>(message = it.errorMessage))
                is ApiResponse.Success -> {
                    val domain = mapLoginResponseToDomain(it.data)
                    emit(Resource.Success(domain))
//                    if (domain.emailVerifiedAt) {
//                        emit(Resource.Success(domain))
//                    } else {
//                        emit(Resource.Error<Login>(message = "Email is not verified"))
//                    }
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun diagnosa(diagnosaForm: DiagnosaForm): Flow<Resource<Diagnosa>> = flow {
        emit(Resource.Loading())
        remoteDataSource.diagnosa(diagnosaForm).collect {
            when (it) {
                is ApiResponse.Empty -> emit(Resource.Error<Diagnosa>(message = "Failed to get data diagnosa"))
                is ApiResponse.Error -> emit(Resource.Error<Diagnosa>(message = it.errorMessage))
                is ApiResponse.Success -> {
                    val domain = mapDiagnosaResponseToDomain(it.data)
                    emit(Resource.Success(domain))
                }
            }
        }
    }

    override suspend fun getHistoryDiagnosa(): Flow<Resource<List<HistoryDiagnosa>>> =
        flow {
            emit(Resource.Loading())
            remoteDataSource.historyDiagnosa().collect {
                when (it) {
                    is ApiResponse.Empty -> emit(Resource.Success<List<HistoryDiagnosa>>(emptyList()))
                    is ApiResponse.Error -> emit(Resource.Error<List<HistoryDiagnosa>>(message = it.errorMessage))
                    is ApiResponse.Success -> {
                        val listDomain = mapHistoryDiagnosaResponseToDomain(it.data)
                        emit(Resource.Success(listDomain))
                    }
                }
            }
        }
}