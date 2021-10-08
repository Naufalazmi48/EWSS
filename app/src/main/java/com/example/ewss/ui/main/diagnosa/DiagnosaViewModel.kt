package com.example.ewss.ui.main.diagnosa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.core.domain.model.Diagnosa
import com.example.core.domain.repository.IRepository
import com.example.core.presentation.model.DiagnosaForm
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DiagnosaViewModel(private val repository: IRepository) : ViewModel() {
    fun diagnosa(diagnosaForm: DiagnosaForm): LiveData<Resource<Diagnosa>> {
        val result = MutableLiveData<Resource<Diagnosa>>()
        viewModelScope.launch {
            repository.diagnosa(diagnosaForm).collect {
                result.postValue(it)
            }
        }
        return result
    }

}