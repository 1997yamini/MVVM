package com.app.demomvvm.data.api


import com.app.demomvvm.utils.Resource
import com.app.demomvvm.utils.SUCCESS
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.coroutines.resume

suspend fun <T : Any> Deferred<Response<T>>.awaitResult(): Result<T> {
    return suspendCancellableCoroutine { continuation ->
        GlobalScope.launch {
            try {
                val response = await()
                val appResponse = response.body() as Resource<*>?
                continuation.resume(
                    appResponse?.let {
                        if (appResponse.Code == SUCCESS) {
                            appResponse.data?.let {
                                Result.Ok(
                                    response.body()!!,
                                    response.raw()
                                )
                            } ?: Result.Exception(
                                AppException(
                                    "oops something went wrong", appResponse.Code
                                )
                            )
                        } else {
                            appResponse.message?.let { error ->
                                Result.Exception(
                                    AppException(
                                        error, appResponse.Code
                                    )
                                )
                            } ?: Result.Exception(
                                AppException(
                                    "oops something went wrong", appResponse.Code
                                )
                            )
                        }
                    } ?: run {
                        Result.Exception(AppException("Please try after sometime."))
                    })

            } catch (e: Throwable) {
                continuation.resume(Result.Exception(AppException(e.message)))
            }
        }
        registerOnCompletion(continuation)
    }
}

private fun Deferred<Response<*>>.registerOnCompletion(continuation: CancellableContinuation<*>) {
    continuation.invokeOnCancellation {
        if (continuation.isCancelled)
            try {
                cancel()
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
    }
}