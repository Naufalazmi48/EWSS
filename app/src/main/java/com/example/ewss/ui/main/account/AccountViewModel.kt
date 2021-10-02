package com.example.ewss.ui.main.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.preferences.UserPreferences
import com.example.core.presentation.model.Account
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AccountViewModel(private val prefs: UserPreferences): ViewModel() {
    fun getDataUser(): LiveData<Account> = prefs.getUser().asLiveData()
    fun logout() {
        viewModelScope.launch { prefs.clearAllData() }
    }
}