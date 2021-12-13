package com.example.ewss.utils

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.ewss.R

object StatusPatient {
    fun checkStatusColor(status: String): Int {
        return when {
            status.equals("Stabil", true) -> R.color.green
            status.equals("Hati-Hati", true) -> R.color.yellow
            status.equals("Waspada", true) -> R.color.orange
            status.equals("Berbahaya", true) -> R.color.red
            else -> R.color.gray
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