package com.app.demomvvm.data.api

import com.app.demomvvm.utils.JsonUtils
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppRetrofit {
    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(JsonUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
    }
    private val client by lazy {
        OkHttpClient.Builder()
            .addNetworkInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()

                /*  Hawk.get<LoginResponse>(LOGIN_RESPONSE, null)?.let {
                      request.addHeader(
                          "Authorization",
                          it.Token
                      )
                      debugLog("accessToken", it.Token)
                  }*/
                request.addHeader("language", "en")
                request.addHeader("loginAs", "User")
                request.addHeader("Accept", "application/json")
                chain.proceed(request.build())
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (JsonUtils.SHOW_LOGS)
                    level = HttpLoggingInterceptor.Level.BODY
                else
                    level = HttpLoggingInterceptor.Level.NONE
            }

            )
            .connectTimeout(120000, TimeUnit.SECONDS)
            .readTimeout(120000, TimeUnit.SECONDS)
            .writeTimeout(120000, TimeUnit.SECONDS)
            .build()
    }

    val apiServices: ApiService by lazy {
        retrofitBuilder.build().create(ApiService::class.java)
    }
}