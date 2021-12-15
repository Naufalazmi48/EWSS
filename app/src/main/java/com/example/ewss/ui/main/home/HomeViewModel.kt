package com.example.ewss.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.core.domain.model.Login
import com.example.core.domain.model.StatisticPatient
import com.example.core.domain.repository.IRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: IRepository) : ViewModel() {
    private val _statistic = MutableLiveData<Resource<StatisticPatient>>()
    val statistic:LiveData<Resource<StatisticPatient>> = _statistic

    fun getStatistic() {
        viewModelScope.launch {
            repository.getStatisticPatient().collect {
                _statistic.postValue(it)
            }
        }
    }
}