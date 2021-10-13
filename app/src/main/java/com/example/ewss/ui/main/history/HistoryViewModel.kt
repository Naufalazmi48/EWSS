package com.example.ewss.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.core.domain.model.HistoryDiagnosa
import com.example.core.domain.repository.IRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: IRepository) : ViewModel() {
    private val _historyDiagnosa = MutableLiveData<Resource<List<HistoryDiagnosa>>>()
    val historyDiagnosa: LiveData<Resource<List<HistoryDiagnosa>>> = _historyDiagnosa

    fun getHistoryDiagnosa() {
        viewModelScope.launch {
            repository.getHistoryDiagnosa().collect {
                _historyDiagnosa.postValue(it)
            }
        }
    }
}