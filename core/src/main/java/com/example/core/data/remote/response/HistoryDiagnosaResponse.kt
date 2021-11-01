package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class HistoryDiagnosaResponse(

	@field:SerializedName("data")
	val data: List<DataDiagnosa?>? = null
)
