package com.example.harrypotterstudents.data.remote

import com.example.harrypotterstudents.data.model.Students
import retrofit2.Response
import retrofit2.http.GET

interface StudentApi {

    @GET(STUDENTS)
    suspend fun getStudents(): Response<Students> //List<Student> = Students

    companion object {
        const val BASE_URL = "https://hp-api.onrender.com/"
        private const val STUDENTS ="api/characters/students"
    }
}