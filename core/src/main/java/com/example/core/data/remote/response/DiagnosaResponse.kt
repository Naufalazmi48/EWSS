package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DiagnosaResponse(

	@field:SerializedName("data")
	val data: DataDiagnosa? = null,

	@field:SerializedName("response")
	val response: Response? = null
)

data class DataDiagnosa(

	@field:SerializedName("Keterangan_Hasil")
	val keteranganHasil: String? = null,

	@field:SerializedName("hasil")
	val hasil: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Response(

	@field:SerializedName("status")
	val status: Int? = null
)
