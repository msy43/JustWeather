package com.msy.justweather.services

import com.msy.justweather.models.WeatherElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiInterface {

    @GET("currentconditions/v1/{q}?apikey=znqUlHXQn0KBc1PudDCbaAAZGGESHba2&details=true")

    fun getWeather(
        @Path("q")q: String
    ): Call<List<WeatherElement>>
}

