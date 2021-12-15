package com.example.ewss.ui.signin

import androidx.lifecycle.*
import com.example.core.data.Resource
import com.example.core.data.preferences.UserPreferences
import com.example.core.domain.model.Login
import com.example.core.domain.repository.IRepository
import com.example.core.presentation.model.Account
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SignInViewModel(private val repository: IRepository, private val prefs: UserPreferences) :
    ViewModel() {

    private val _login = MutableLiveData<Resource<Login>>()
    val login:LiveData<Resource<Login>> = _login

    fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.login(email, password).collect {
                _login.postValue(it)
            }
        }
    }

    fun alreadyLoggedIn(): LiveData<Account> {
        return prefs.getUser().asLiveData()
    }

    fun saveUserOnPreferences(account: Account) {
        viewModelScope.launch {
            account.let {
                prefs.saveUser(name = it.name, phone = it.phone, email = it.email)
            }
        }
    }
}