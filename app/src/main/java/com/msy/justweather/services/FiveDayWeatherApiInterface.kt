package com.msy.justweather.services

import com.msy.justweather.models.FiveDayWeather
import com.msy.justweather.models.WeatherElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FiveDayWeatherApiInterface {

    @GET("forecasts/v1/daily/5day/{q}?apikey=znqUlHXQn0KBc1PudDCbaAAZGGESHba2&metric=true")

    fun getWeather(
        @Path("q")q: String
    ): Call<FiveDayWeather>
}