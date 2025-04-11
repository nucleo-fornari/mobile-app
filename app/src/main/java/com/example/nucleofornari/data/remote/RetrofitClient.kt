package com.example.nucleofornari.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetrofitClient {
//    private const val BASE_URL = "http://192.168.1.4:8080/"
//
//    private val interceptor = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//
//    private val httpClient = OkHttpClient.Builder()
//        .addInterceptor(interceptor)
//        .build()
//
//    val instance: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(httpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//}

object RetrofitClient {
    fun getInstance(token: String): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(TokenInterceptor(token))
            .build()

        return Retrofit.Builder()
            .baseUrl("http://192.168.1.4:8080/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}