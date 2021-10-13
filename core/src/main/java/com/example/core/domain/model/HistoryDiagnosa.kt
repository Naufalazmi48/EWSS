package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryDiagnosa(
    val usia: Int,
    val pernafasan: Int,
    val denyutNadi: Int,
    val tingkatKesadaran: String,
    val tekananDarah: Int,
    val nama: String,
    val suhu: Int,
    val id: Int,
    val alamat: String,
    var diagnosaResult: Diagnosa?
): Parcelable
