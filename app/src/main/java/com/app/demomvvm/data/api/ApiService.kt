package com.app.demomvvm.data.api

import com.app.demomvvm.data.model.request.ContactUsRequest
import com.app.demomvvm.data.model.response.User
import com.app.demomvvm.utils.Resource
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("contact/add")
    fun contactUsAsync(@Body req: ContactUsRequest): Deferred<Response<Resource<Any>>>

}