package com.msy.justweather.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msy.justweather.models.DailyForecast
import com.msy.justweather.models.FiveDayWeather

class FiveDayWeatherAdapter(private var fiveDay: ArrayList<DailyForecast>) : RecyclerView.Adapter<FiveDayWeatherHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDayWeatherHolder {
        return FiveDayWeatherHolder(parent)
    }

    override fun getItemCount(): Int = fiveDay.size

    override fun onBindViewHolder(holder: FiveDayWeatherHolder, position: Int) {
        holder.bindTo(fiveDay[position])

    }
}