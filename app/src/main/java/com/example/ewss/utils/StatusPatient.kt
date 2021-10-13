package com.example.ewss.utils

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.ewss.R

object StatusPatient {
    fun Context.checkStatusColor(status: String): Int {
        val listStatus = this.resources.getStringArray(R.array.status_patient)
        return when {
            status.equals(listStatus[0], true) -> R.color.red
            status.equals(listStatus[1], true) -> R.color.orange
            status.equals(listStatus[2], true) -> R.color.yellow
            else -> R.color.green
        }
    }

    fun Context.setKesadaranAdapter(autoCompleteTextView: AutoCompleteTextView) {
        val statusKesadaran = this.resources.getStringArray(R.array.status_kesadaran)
        val adapterStatusKesadaran = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            statusKesadaran
        )

        autoCompleteTextView.setAdapter(adapterStatusKesadaran)
    }
}