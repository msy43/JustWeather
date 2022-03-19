package com.msy.justweather

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msy.justweather.adapter.AddCityAdapter
import com.msy.justweather.database.CityDatabase
import com.msy.justweather.database.CityEntity
import com.msy.justweather.databinding.ActivityAddCityBinding
import com.msy.justweather.databinding.ActivityMainBinding
import com.msy.justweather.models.AutoCompleteElement
import com.msy.justweather.services.AutoCompleteApiInterface
import com.msy.justweather.services.AutoCompleteApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCity : AppCompatActivity() {

    private lateinit var addCityActivityBinding : ActivityAddCityBinding

    lateinit var recyclerView: RecyclerView
    lateinit var searchEditText: EditText
    lateinit var addCityButton: Button

    lateinit var cityEntity: CityEntity
    var citys = ArrayList<AutoCompleteElement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addCityActivityBinding = ActivityAddCityBinding.inflate(layoutInflater)
        val view = addCityActivityBinding.root

        recyclerView = addCityActivityBinding.cityList
        searchEditText = addCityActivityBinding.cityTextInput
        addCityButton = addCityActivityBinding.addCityButton

        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                citys.clear()
                getAutoCompleteData(searchEditText.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }

        addCityButton.setOnClickListener {
            insertToDatabase()
            toMainActivity2()
        }

        setContentView(view)
    }

    fun toMainActivity2(){
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
        finish()
    }

    fun insertToDatabase(){

        val cityDatabase: CityDatabase = CityDatabase.getFavoritesDatabase(this)!!

        val sharedPreference =  addCityActivityBinding.root.context.getSharedPreferences("addcity", Context.MODE_PRIVATE)
        val key = sharedPreference.getString("key","0")
        val cityName = sharedPreference.getString("cityName","0")
        val cityProvince = sharedPreference.getString("cityProvince","0")
        val cityCountry = sharedPreference.getString("cityCountry","0")

        cityEntity = CityEntity (0, key.toString(), cityName.toString(), cityProvince.toString(), cityCountry.toString())
        cityDatabase.cityDAO().insert(cityEntity)
        Log.v("TAG", cityDatabase.cityDAO().getAllCity().toString())
        Log.v("TAG", cityDatabase.cityDAO().selectRowGetKey(0).toString())
    }

    private fun getAutoCompleteData(q: String) {
        val apiService = AutoCompleteApiService.getInstance().create(AutoCompleteApiInterface::class.java)
        apiService.getCitys(q,"tr-tr").enqueue(object: Callback<List<AutoCompleteElement>> {

            override fun onFailure(call: Call<List<AutoCompleteElement>>, t: Throwable) {
                Log.d("TAG", "err $t")
            }

            override fun onResponse(call: Call<List<AutoCompleteElement>>, response: Response<List<AutoCompleteElement>>) {
                if (response.isSuccessful){
                    val adapter = AddCityAdapter(citys, addCityActivityBinding.root.context)
                    citys.addAll(response.body()!!)
                    recyclerView.layoutManager = LinearLayoutManager(addCityActivityBinding.root.context)
                    recyclerView.adapter = adapter
                }
            }
        })
    }
}