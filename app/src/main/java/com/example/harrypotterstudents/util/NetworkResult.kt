package com.example.harrypotterstudents.util

sealed class NetworkResult<out T> {

    class Success<T>(val response: T): NetworkResult<T>()
    class Error<T>(val error: Exception): NetworkResult<T>()
    object Loading : NetworkResult<Nothing>()

}

//sealed class NetworkResultm<T>(
//    val data: T? = null,
//    val message: String? = null
//) {
//    class Success<T>(data: T?) : NetworkResult<T>(data)
//    class Error<T>(message: String) : NetworkResult<T>(message = message)
//    class Loading<T> : NetworkResult<T>()
//}