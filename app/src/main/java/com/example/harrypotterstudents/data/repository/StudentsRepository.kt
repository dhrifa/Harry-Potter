package com.example.harrypotterstudents.data.repository

import com.example.harrypotterstudents.data.model.Students
import com.example.harrypotterstudents.data.remote.StudentApi
import com.example.harrypotterstudents.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

interface StudentsRepository {
    suspend fun getStudents(): Flow<NetworkResult<Students>>
}

class StudentsRepositoryImpl @Inject constructor(
    private val studentApi: StudentApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : StudentsRepository {
    override suspend fun getStudents(): Flow<NetworkResult<Students>> = flow {
        emit(NetworkResult.Loading)

        try {
            val response = studentApi.getStudents()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(NetworkResult.Success(it))
                } ?: throw Exception("null students response")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (ex: Exception) {
            emit(NetworkResult.Error(ex))
        }
    }.flowOn(ioDispatcher)

}