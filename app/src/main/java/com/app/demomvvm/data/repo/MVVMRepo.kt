package com.app.demomvvm.data.repo

import com.app.demomvvm.data.api.ApiService
import com.app.demomvvm.data.api.networkCall
import com.app.demomvvm.data.model.request.ContactUsRequest

class MVVMRepo(private val services: ApiService) {

    //make all functions to hit API
    fun contactUs(req: ContactUsRequest) = networkCall<Any> {
        client = services.contactUsAsync(req)
    }

}