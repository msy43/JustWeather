package com.msy.justweather

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msy.justweather.adapter.FiveDayWeatherAdapter
import com.msy.justweather.adapter.WeatherAdapter
import com.msy.justweather.database.CityDatabase
import com.msy.justweather.databinding.ActivityMain2Binding
import com.msy.justweather.models.FiveDayWeather
import com.msy.justweather.models.WeatherElement
import com.msy.justweather.services.FiveDayWeatherApiService
import com.msy.justweather.services.FiveDayWeatherApiInterface
import com.msy.justweather.services.WeatherApiInterface
import com.msy.justweather.services.WeatherApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity2 : AppCompatActivity() {

    private lateinit var mainActivity2Binding : ActivityMain2Binding


    lateinit var recyclerView: RecyclerView

    var weather = ArrayList<WeatherElement>()
    val adapter = WeatherAdapter(weather)


    lateinit var dateText: TextView
    lateinit var cityText: TextView

    lateinit var backCity: ImageView
    lateinit var nextCity: ImageView

    lateinit var cityDatabase: CityDatabase
    lateinit var cityId: String

    var index = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity2Binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = mainActivity2Binding.root

        val df: DateFormat = SimpleDateFormat("d MMM yyyy")

        val date: String = df.format(Calendar.getInstance().time)

        cityDatabase = CityDatabase.getFavoritesDatabase(this)!!

        dateText = mainActivity2Binding.date

        dateText.text = date

        cityText = mainActivity2Binding.bottomCityText

        backCity = mainActivity2Binding.backCity
        nextCity = mainActivity2Binding.nextCity

        recyclerView = mainActivity2Binding.weatherListRecycler
        recyclerView.layoutManager = LinearLayoutManager(mainActivity2Binding.root.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        }

        getDatabase(index)

        getWeatherData(cityId)

        checkCityNumber()

        nextCity.setOnClickListener {
            weather.clear()
            nextCity()
            getWeatherData(cityId)
        }

        setContentView(view)
    }

    private fun nextCity() {
        var cityCount = cityDatabase.cityDAO().getCount()
        Log.v("TAG", cityCount.toString())

        if (index <= cityCount-1 || cityCount == 1) {
            index += 1

            getDatabase(index)

            Log.v("TAG", cityId)
            Log.v("TAG", index.toString())

            checkCityNumber()

        }

    }

    private fun backCity() {

        var cityCount = cityDatabase.cityDAO().getCount()
        Log.v("TAG", cityCount.toString())

        if (index >= cityCount-1 || cityCount == index) {
            index -= 1

            getDatabase(index)

            Log.v("TAG", cityId)
            Log.v("TAG", index.toString())

            checkCityNumber()

        }

    }
    private fun checkCityNumber() {
        var cityCount = cityDatabase.cityDAO().getCount()

        if (index == cityCount-1 || cityCount == 1) {
            nextCity.setImageResource(R.drawable.ic_round_location_city_24)
            nextCity.setOnClickListener {
                toAddCity()
            }

        } else {
            nextCity.setImageResource(R.drawable.ic_round_arrow_forward_24)
        }
    }

    fun toAddCity(){
        val intent = Intent(this,AddCity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getDatabase(row: Int) {
        cityId = cityDatabase.cityDAO().selectRowGetKey(row).toString()
        cityText.text = cityDatabase.cityDAO().selectKeyGetName(cityId)
        Log.v("TAG", cityId)
    }

    private fun getWeatherData(id:String) {
        val apiService = WeatherApiService.getInstance().create(WeatherApiInterface::class.java)
        apiService.getWeather(id).enqueue(object: Callback<List<WeatherElement>> {

            override fun onFailure(call: Call<List<WeatherElement>>, t: Throwable) {
                Log.d("TAG", "err $t")
            }

            override fun onResponse(call: Call<List<WeatherElement>>, response: Response<List<WeatherElement>>) {
                if (response.isSuccessful) {
                    Log.d("TAG", "You did it MSY !!")

                    weather.addAll(response.body()!!)

                    recyclerView.adapter = adapter

                }
            }
        })
    }

}
