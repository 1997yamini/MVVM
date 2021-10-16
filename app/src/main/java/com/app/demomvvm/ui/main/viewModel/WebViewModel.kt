package com.app.demomvvm.ui.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.demomvvm.data.model.request.ContactUsRequest
import com.app.demomvvm.data.repo.MVVMRepo

class WebViewModel (private val repo: MVVMRepo): ViewModel(){

    private val contactUsRequest by lazy {MutableLiveData<ContactUsRequest>()}

    val contactUsLiveData = Transformations.switchMap(contactUsRequest){
        repo.contactUs(it)
    }

    fun contactUs(req:ContactUsRequest){
        contactUsRequest.value =req
    }
}