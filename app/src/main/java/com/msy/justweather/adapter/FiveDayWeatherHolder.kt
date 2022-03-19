package com.msy.justweather.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.msy.justweather.R
import com.msy.justweather.models.AutoCompleteElement
import com.msy.justweather.models.DailyForecast
import com.msy.justweather.models.FiveDayWeather
import com.msy.justweather.models.WeatherElement
import com.squareup.picasso.Picasso

class FiveDayWeatherHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.five_day_desing, viewGroup, false)) {

    private val day by lazy { itemView.findViewById<TextView>(R.id.fiveDay_Day) }

    private val icon by lazy { itemView.findViewById<ImageView>(R.id.fiveDay_icon) }

    private val degree by lazy { itemView.findViewById<TextView>(R.id.fiveDay_temp) }

    fun bindTo(fiveDay: DailyForecast) {

        day.text = fiveDay.date.take(10).takeLast(2)

        val degreeString =
            "${fiveDay.temperature.minimum.value} " +
                    "${fiveDay.temperature.minimum.unit} / " +
                    "${fiveDay.temperature.maximum.value} " +
                    "${fiveDay.temperature.maximum.unit}"

        degree.text = degreeString


        val iconLong = fiveDay.day.icon
        var iconString: String

        if (iconLong < 10 ){
            iconString = "0${iconLong}"
        } else {
            iconString = iconLong.toString()
        }
        val detailImageUrlFull = "https://developer.accuweather.com/sites/default/files/${iconString}-s.png"

        Log.d("TAG", detailImageUrlFull)

        Picasso.get().load(detailImageUrlFull).into(icon)


    }

}


/*
var fiveDayWeather = ArrayList<FiveDayWeather>()
        val adapter2 = FiveDayWeatherAdapter(fiveDayWeather)

        var recyclerView2: RecyclerView = view.findViewById(R.id.fiveDailyLayout)

        var cityDatabase: CityDatabase = CityDatabase.getFavoritesDatabase(view.context)!!
        val cityId = cityDatabase.cityDAO().selectRowGetKey(0).toString()

        val apiService = FiveDayWeatherApiService.getInstance().create(FiveDayWeatherApiInterface::class.java)
        apiService.getWeather(cityId).enqueue(object: Callback<List<FiveDayWeather>> {
            override fun onResponse(call: Call<List<FiveDayWeather>>, response: Response<List<FiveDayWeather>>) {
                fiveDayWeather.addAll(response.body()!!)
                recyclerView2.adapter = adapter2
            }

            override fun onFailure(call: Call<List<FiveDayWeather>>, t: Throwable) {
                Log.d("TAG", "err $t")
            }

        })

 */
