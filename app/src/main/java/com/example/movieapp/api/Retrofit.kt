package com.example.movieapp.api

import com.google.errorprone.annotations.Keep
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Keep
object RetrofitBuilder {

    private val httpClient = OkHttpClient.Builder()
    private val logging = HttpLoggingInterceptor()

    private fun getRetrofit(): Retrofit {
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}