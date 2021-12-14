package com.example.ewss.utils

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
}