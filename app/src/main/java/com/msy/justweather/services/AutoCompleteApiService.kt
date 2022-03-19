package com.msy.justweather.services

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

class AutoCompleteApiService {

    companion object {
        private const val BASE_URL = "https://dataservice.accuweather.com/"

        private var retrofit : Retrofit? = null
        private val contentType = MediaType.get("application/json")

        fun getInstance() : Retrofit{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(Json.asConverterFactory(contentType))
                    .build()
            }
            return retrofit!!
        }
    }

}