package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class StatisticResponse(

	@field:SerializedName("data")
	val data: DataStatistic? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataStatistic(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("waspada")
	val waspada: Int? = null,

	@field:SerializedName("hati_hati")
	val hatiHati: Int? = null,

	@field:SerializedName("stabil")
	val stabil: Int? = null,

	@field:SerializedName("berbahaya")
	val berbahaya: Int? = null
)
