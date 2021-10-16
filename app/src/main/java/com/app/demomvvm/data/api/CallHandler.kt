package com.app.demomvvm.data.api

import androidx.lifecycle.MutableLiveData
import com.app.demomvvm.utils.Resource
import com.app.demomvvm.utils.Resource.Companion.loading
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

class CallHandler<T:Any> {

    lateinit var client:Deferred<Response<Resource<T>>>

    fun makeCall():MutableLiveData<Resource<T>> {
        val result = MutableLiveData<Resource<T>>()
        result.value = Resource.loading(null)

        GlobalScope.launch {
            try {
                val response = client.awaitResult().getOrThrow()
                withContext(Dispatchers.Main) {
                    result.value = Resource.success(
                        response.data
                    )
                }
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    if (e is UnknownHostException || e is ConnectException) {

                        result.value = Resource.error("pls check internet")
                    } else if (e is HttpException) {
                        result.value = Resource.error(
                            "${e.message} | code ${e.response()?.code()}"
                        )
                    } else {
                        result.value =
                            Resource.error(
                                "${e.message}",
                                if (e is AppException) (e).code else -1
                            )
                    }

                }
                e.printStackTrace()

            }
        }
        return result

    }
}

fun <T : Any> networkCall(b: CallHandler<T>.() -> Unit): MutableLiveData<Resource<T>>? =
    CallHandler<T>().apply(b).makeCall()

interface DataResponse<T> {
    fun retrieveData(): T?
}