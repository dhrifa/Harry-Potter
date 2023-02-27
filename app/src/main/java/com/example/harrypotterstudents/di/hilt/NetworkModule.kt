package com.example.harrypotterstudents.di.hilt

import com.example.harrypotterstudents.data.remote.StudentApi
import com.example.harrypotterstudents.data.repository.StudentsRepository
import com.example.harrypotterstudents.data.repository.StudentsRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideClient(
//        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor( loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(StudentApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    fun provideApiDetails(
        retrofit: Retrofit
    ):StudentApi{
        return retrofit.create(StudentApi::class.java)
    }

    @Provides
    fun provideRepository(
        studentApi: StudentApi
    ): StudentsRepository{
        return StudentsRepositoryImpl(studentApi)
    }
}