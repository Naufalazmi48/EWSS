package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class HistoryDiagnosaResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItem(

	@field:SerializedName("usia")
	val usia: Int? = null,

	@field:SerializedName("pernafasan")
	val pernafasan: Int? = null,

	@field:SerializedName("denyut_nadi")
	val denyutNadi: Int? = null,

	@field:SerializedName("tingkat_kesadaran")
	val tingkatKesadaran: String? = null,

	@field:SerializedName("tekanan_darah")
	val tekananDarah: Int? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("suhu")
	val suhu: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
