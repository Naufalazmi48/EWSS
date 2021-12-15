package com.example.ewss.di

import com.example.ewss.ui.main.account.AccountViewModel
import com.example.ewss.ui.main.diagnosa.DiagnosaViewModel
import com.example.ewss.ui.main.history.HistoryViewModel
import com.example.ewss.ui.main.home.HomeViewModel
import com.example.ewss.ui.register.RegisterViewModel
import com.example.ewss.ui.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignInViewModel(get(), get()) }
    viewModel { AccountViewModel(get()) }
    viewModel { DiagnosaViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}