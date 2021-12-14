package com.example.ewss.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.core.domain.model.Register
import com.example.core.domain.repository.IRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: IRepository): ViewModel() {
    private val _register = MutableLiveData<Resource<Boolean>>()
    val registerObserver: LiveData<Resource<Boolean>> = _register

    fun register(register: Register) {
        viewModelScope.launch {
            repository.register(register).collect {
                _register.postValue(it)
            }
        }
    }
}