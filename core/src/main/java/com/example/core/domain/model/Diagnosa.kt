package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Diagnosa(
    val result: String,
    val detailResult: String,
): Parcelable
