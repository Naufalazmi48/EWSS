package com.example.core.utils

import com.example.core.data.remote.response.DataDiagnosa
import com.example.core.data.remote.response.DataLogin
import com.example.core.data.remote.response.DataStatistic
import com.example.core.domain.model.Diagnosa
import com.example.core.domain.model.HistoryDiagnosa
import com.example.core.domain.model.Login
import com.example.core.domain.model.StatisticPatient

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

    fun mapDiagnosaResponseToDomain(dataDiagnosa: DataDiagnosa): Diagnosa =
        Diagnosa(
            result = dataDiagnosa.hasil ?: "-",
            detailResult = dataDiagnosa.keteranganHasil ?: "-"
        )

    fun mapHistoryDiagnosaResponseToDomain(listDataHistoryDiagnosa: List<DataDiagnosa>): List<HistoryDiagnosa> =
        listDataHistoryDiagnosa.map {
            HistoryDiagnosa(
                usia = it.usia ?: -1,
                pernafasan = it.pernafasan ?: -1,
                denyutNadi = it.denyutNadi ?: -1,
                tingkatKesadaran = it.tingkatKesadaran ?: "-",
                tekananDarah = it.tekananDarah ?: -1,
                nama = it.nama ?: "-",
                suhu = it.suhu ?: -1.0,
                id = it.id ?: -1,
                alamat = it.alamat ?: "-",
                diagnosaResult = Diagnosa(result = it.hasil ?: "-", detailResult = it.keteranganHasil ?: "-")
            )
        }

    fun mapStatisticResponseToDomain(dataStatistic: DataStatistic): StatisticPatient =
        StatisticPatient(
            total = dataStatistic.total ?: 0,
            waspada = dataStatistic.waspada ?: 0,
            hatiHati = dataStatistic.hatiHati ?: 0,
            stabil = dataStatistic.stabil ?: 0,
            berbahaya = dataStatistic.berbahaya ?: 0
        )
}