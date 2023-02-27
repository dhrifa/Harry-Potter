package com.example.harrypotterstudents.di.koin

import com.example.harrypotterstudents.data.remote.StudentApi
import com.example.harrypotterstudents.data.repository.StudentsRepository
import com.example.harrypotterstudents.data.repository.StudentsRepositoryImpl
import com.example.harrypotterstudents.ui.students.StudentsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single <OkHttpClient>{
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single <StudentApi>{
        Retrofit.Builder()
            .baseUrl(StudentApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(StudentApi::class.java)
    }
}

val repoModule = module {
    single<StudentsRepository> { StudentsRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel{ StudentsViewModel(get())}
}