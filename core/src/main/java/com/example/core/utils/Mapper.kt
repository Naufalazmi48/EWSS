package com.example.core.utils

import com.example.core.data.remote.response.DataLogin
import com.example.core.domain.model.Login

object Mapper {
    fun mapLoginResponseToDomain(dataLogin: DataLogin): Login =
        Login(
            id = dataLogin.id ?: -1,
            nama = dataLogin.nama ?: "-",
            email = dataLogin.email ?: "-",
            country = dataLogin.country ?: "-",
            updatedAt = dataLogin.updatedAt ?: "-",
            phone = dataLogin.phone ?: "-",
            city = dataLogin.city ?: "-",
            createdAt = dataLogin.createdAt ?: "-",
            emailVerifiedAt = dataLogin.emailVerifiedAt != null
        )
}