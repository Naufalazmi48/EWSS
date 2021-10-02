package com.example.core.di

import androidx.datastore.preferences.preferencesDataStore
import com.example.core.BuildConfig
import com.example.core.data.Repository
import com.example.core.data.preferences.UserPreferences
import com.example.core.data.remote.RemoteDataSource
import com.example.core.data.remote.network.ApiService
import com.example.core.domain.repository.IRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ewss-app.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get(), get()) }
    single<IRepository> { Repository(get()) }
}

val preferencesModule = module {
    single {
        preferencesDataStore(name = "user").getValue(
            androidContext(),
            UserPreferences::javaClass
        )
    }
    single {
        UserPreferences(get())
    }
}