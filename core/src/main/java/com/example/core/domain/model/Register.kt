package com.example.core.domain.model

data class Register(
    val name: String,
    val email: String,
    val phone: String,
    val country: String,
    val city: String,
    val password: String,
    val passwordConfirmation: String,
)
