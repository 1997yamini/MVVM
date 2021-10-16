package com.app.demomvvm.data.api
import okhttp3.Response
import retrofit2.HttpException


sealed class Result<out T : Any> {

    class Ok<out T : Any>(
        val value: T,
        override val response: Response
    ) : Result<T>(),
        ResponseResult {
        override fun toString() = "Result.Ok{value=$value, response=$response}"
    }


    class Error(
        override val exception: HttpException,
        override val response: Response
    ) : Result<Nothing>(),
        ErrorResult,
        ResponseResult {
        override fun toString() = "Result.Error{exception=$exception}"
    }

    class Exception(
        override val exception: AppException
    ) : Result<Nothing>(),
        ErrorResult {
        override fun toString() = "Result.Exception{$exception}"
    }

}


interface ResponseResult {
    val response: Response
}


interface ErrorResult {
    val exception: Throwable
}

fun <T : Any> Result<T>.getOrNull(): T? =
    (this as? Result.Ok)?.value

fun <T : Any> Result<T>.getOrDefault(default: T): T =
    getOrNull() ?: default

fun <T : Any> Result<T>.getOrThrow(throwable: AppException? = null): T {
    return when (this) {
        is Result.Ok -> value
        is Result.Error -> throw throwable ?: exception
        is Result.Exception -> throw throwable ?: exception
    }
}