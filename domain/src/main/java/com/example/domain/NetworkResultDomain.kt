package com.example.domain

sealed class NetworkResultDomain<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResultDomain<T>(data)
    class Error<T>(message: String?, data: T? = null) : NetworkResultDomain<T>(data)
    class Loading<T> : NetworkResultDomain<T>()
}
