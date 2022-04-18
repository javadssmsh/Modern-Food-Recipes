package com.example.domain.base

sealed class ResultDomain<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : ResultDomain<T>(data)
    class Error<T>(message: String?, data: T? = null) : ResultDomain<T>(data)
    class Loading<T> : ResultDomain<T>()
}
