package com.msy.justweather.services

import com.msy.justweather.models.AutoCompleteElement
import com.msy.justweather.models.WeatherElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AutoCompleteApiInterface {

    @GET("locations/v1/cities/autocomplete?apikey=znqUlHXQn0KBc1PudDCbaAAZGGESHba2&")

    fun getCitys(
        @Query("q")query: String,
        @Query("language")query2: String
    ): Call<List<AutoCompleteElement>>
}