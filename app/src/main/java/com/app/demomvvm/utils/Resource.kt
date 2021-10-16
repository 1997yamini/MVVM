package com.app.demomvvm.utils

class Resource<T> private constructor(  var status: String,
                             val data: T? = null,
                             val message: String? = null,
                             val Code: Int? = null) {
/*
    if you want to write a function or any member
    of the class that can be called without having the
    instance of the class then you can write the same as a
    member of a companion object inside the class.
*/
    companion object{

    fun <T> loading(message: String?) = Resource<T>(AMFIXO_HTTPS_LOADING, message = message)

    fun <T> success(data: T?) = Resource(AMFIXO_HTTPS_SUCCESS, data)

    fun <T> error(message: String?,statusCode: Int?=null) = Resource<T>(AMFIXO_HTTPS_FAILURE, message = message,Code = statusCode)

}
}