package com.msy.justweather.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.msy.justweather.models.WeatherElement


class WeatherAdapter (private var weather: List<WeatherElement>) : RecyclerView.Adapter<WeatherHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        return WeatherHolder(parent)
    }

    override fun getItemCount(): Int = weather.size

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bindTo(weather[position])

    }
}