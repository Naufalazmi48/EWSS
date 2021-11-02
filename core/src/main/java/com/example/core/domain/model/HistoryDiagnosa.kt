package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryDiagnosa(
    val usia: Int,
    val pernafasan: Double,
    val denyutNadi: Double,
    val tingkatKesadaran: String,
    val tekananDarah: Double,
    val nama: String,
    val suhu: Double,
    val id: Int,
    val alamat: String,
    var diagnosaResult: Diagnosa?
): Parcelable
