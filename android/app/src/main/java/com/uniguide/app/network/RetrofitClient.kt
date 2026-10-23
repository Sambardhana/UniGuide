package com.uniguide.app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // 💡 10.0.2.2 routes to your PC's localhost from the Android Emulator
    private const val BASE_URL = "http://10.122.220.251:8080/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    val chatApi: ChatApi by lazy {
        retrofit.create(ChatApi::class.java)
    }

    val uniGuideApi: UniGuideApi by lazy {
        retrofit.create(UniGuideApi::class.java)
    }
}