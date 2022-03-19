package com.msy.justweather.adapter

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.msy.justweather.R
import com.msy.justweather.database.CityDatabase
import com.msy.justweather.database.CityEntity
import com.msy.justweather.databinding.ListDesingBinding
import com.msy.justweather.models.AutoCompleteElement


class AddCityAdapter (private var citys: List<AutoCompleteElement>, val context: Context) :
    RecyclerView.Adapter<AddCityAdapter.ViewHolder>(){

    var lastCheckedPosition: Int = 0

    class ViewHolder(private val listBinding : ListDesingBinding) : RecyclerView.ViewHolder(listBinding.root){

        val sharedPreference: SharedPreferences = listBinding.root.context.getSharedPreferences("addcity",Context.MODE_PRIVATE)

        fun bindItems(citys: AutoCompleteElement){

            val listString = "${citys.localizedName},${citys.administrativeArea.localizedName},${citys.country.localizedName}"

            listBinding.cityListItem.text = listString

            listBinding.cityListItem.setOnClickListener {

                Log.v("TAG",citys.localizedName)
                val editor = sharedPreference.edit()
                editor.clear()
                editor.putString("key",citys.key)
                editor.putString("cityName", citys.localizedName)
                editor.putString("cityProvince", citys.administrativeArea.localizedName)
                editor.putString("cityCountry", citys.country.localizedName)
                editor.apply()

                Log.v("TAG", sharedPreference.getString("cityName","0").toString())
                Toast.makeText(listBinding.root.context,"${citys.localizedName} is selected", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_desing, parent, false)
        val view : ListDesingBinding = ListDesingBinding.bind(v)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return citys.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(citys[position])
    }

}