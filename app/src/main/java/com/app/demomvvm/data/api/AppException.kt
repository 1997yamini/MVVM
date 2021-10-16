package com.app.demomvvm.data.api

class AppException(val msg:String?,val code:Int?=null) : Throwable(msg)