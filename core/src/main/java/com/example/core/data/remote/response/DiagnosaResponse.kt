package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DiagnosaResponse(

	@field:SerializedName("data")
	val data: DataDiagnosa? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataDiagnosa(

	@field:SerializedName("usia")
	val usia: String? = null,

	@field:SerializedName("pernafasan")
	val pernafasan: String? = null,

	@field:SerializedName("denyut_nadi")
	val denyutNadi: String? = null,

	@field:SerializedName("tingkat_kesadaran")
	val tingkatKesadaran: String? = null,

	@field:SerializedName("tekanan_darah")
	val tekananDarah: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("suhu")
	val suhu: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
