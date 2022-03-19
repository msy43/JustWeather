package com.msy.justweather.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msy.justweather.R
import com.msy.justweather.database.CityDatabase
import com.msy.justweather.models.DailyForecast
import com.msy.justweather.models.FiveDayWeather
import com.msy.justweather.models.WeatherElement
import com.msy.justweather.services.FiveDayWeatherApiInterface
import com.msy.justweather.services.FiveDayWeatherApiService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.body_desing, viewGroup, false)) {

    private val weatherIcon by lazy { itemView.findViewById<ImageView>(R.id.weatherIcon) }

    private val weatherText by lazy { itemView.findViewById<TextView>(R.id.weatherText) }

    private val degree by lazy { itemView.findViewById<TextView>(R.id.degree) }

    private val humidty by lazy { itemView.findViewById<TextView>(R.id.humidty) }

    private val realFeelTemperatureText by lazy { itemView.findViewById<TextView>(R.id.realFeelTemperatureAmount) }
    private val realFeelTemperatureSize by lazy { itemView.findViewById<TextView>(R.id.realFeelTemperatureSize) }

    private val uvText by lazy { itemView.findViewById<TextView>(R.id.uvAmount) }
    private val uvSize by lazy { itemView.findViewById<TextView>(R.id.uvSize) }

    private val windSize by lazy { itemView.findViewById<TextView>(R.id.windSize) }
    private val windDirection by lazy { itemView.findViewById<TextView>(R.id.windDirection) }

    private val pressureText by lazy { itemView.findViewById<TextView>(R.id.pressureAmount) }
    private val pressureSize by lazy { itemView.findViewById<TextView>(R.id.pressureSize) }

    private val recyclerView2 by lazy { itemView.findViewById<RecyclerView>(R.id.fiveDailyRecycleView)}

    var fiveDayWeather = ArrayList<DailyForecast>()
    val adapter2 = FiveDayWeatherAdapter(fiveDayWeather)


    fun bindTo(weather: WeatherElement) {


        weatherText.text = weather.weatherText

        val degreeString = "${weather.temperature.metric.value} ${weather.temperature.metric.unit} "
        degree.text = degreeString

        val humidityString = "% ${weather.relativeHumidity}"
        humidty.text = humidityString

        val realFeelTemperatureString = "${weather.realFeelTemperature.metric.value} ${weather.realFeelTemperature.metric.unit}"
        realFeelTemperatureSize.text = realFeelTemperatureString
        realFeelTemperatureText.text = weather.realFeelTemperature.metric.phrase.toString()

        uvText.text = weather.uvIndexText
        uvSize.text = weather.uvIndex.toString()

        val windString = "${weather.wind.speed.metric.value} ${weather.wind.speed.metric.unit}"
        windSize.text = windString
        windDirection.text = weather.wind.direction.english

        val pressureString = "${weather.pressure.metric.value} ${weather.pressure.metric.unit}"
        pressureSize.text = pressureString
        pressureText.text = weather.pressureTendency.localizedText

        val iconLong = weather.weatherIcon
        var iconString: String

        if (iconLong < 10 ){
            iconString = "0${iconLong}"
        } else {
            iconString = iconLong.toString()
        }
        val detailImageUrlFull = "https://developer.accuweather.com/sites/default/files/${iconString}-s.png"

        Log.d("TAG", detailImageUrlFull)

        Picasso.get().load(detailImageUrlFull).into(weatherIcon)

        recyclerView2.setHasFixedSize(true)


        recyclerView2.apply {
            recyclerView2.layoutManager = LinearLayoutManager(recyclerView2.context,LinearLayoutManager.HORIZONTAL,false)
        }

        var cityDatabase: CityDatabase = CityDatabase.getFavoritesDatabase(itemView.context)!!
        val cityId = cityDatabase.cityDAO().selectRowGetKey(0).toString()

        val apiService = FiveDayWeatherApiService.getInstance().create(FiveDayWeatherApiInterface::class.java)
        apiService.getWeather(cityId).enqueue(object: Callback<FiveDayWeather> {
            override fun onResponse(call: Call<FiveDayWeather>, response: Response<FiveDayWeather>) {
                Log.d("TAG", response.body().toString())
                fiveDayWeather.addAll(response.body()!!.dailyForecasts)
                recyclerView2.adapter = adapter2
            }

            override fun onFailure(call: Call<FiveDayWeather>, t: Throwable) {
                Log.d("TAG", "err $t")
            }
        })
    }

}

/*

        */

