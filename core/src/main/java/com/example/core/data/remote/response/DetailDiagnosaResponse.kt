package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailDiagnosaResponse(
    @field:SerializedName("label")
    val data: DataDiagnosa? = null,
)
