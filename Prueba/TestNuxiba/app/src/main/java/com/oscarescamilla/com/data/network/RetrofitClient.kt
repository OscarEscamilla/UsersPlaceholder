package com.oscarescamilla.com.data.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// se crea como objeto para incializar solo una vez
object RetrofitClient {

    // inicializamos lazy para inicializar solo cuando se necesite
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) // usar como converter a GSon
            .build()
            .create(WebService::class.java)
    }

}