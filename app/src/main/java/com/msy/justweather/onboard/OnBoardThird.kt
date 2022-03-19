package com.msy.justweather.onboard

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msy.justweather.adapter.AutoCompleteAdapter
import com.msy.justweather.adapter.WeatherAdapter
import com.msy.justweather.database.CityDatabase
import com.msy.justweather.database.CityEntity
import com.msy.justweather.databinding.FragmentOnBoardSecondBinding
import com.msy.justweather.databinding.FragmentOnBoardThirdBinding
import com.msy.justweather.models.AutoCompleteElement
import com.msy.justweather.models.WeatherElement
import com.msy.justweather.services.AutoCompleteApiInterface
import com.msy.justweather.services.AutoCompleteApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OnBoardThird : Fragment() {

    private var _binding : FragmentOnBoardThirdBinding? = null
    private val onBoardThirdBinding get() = _binding!!

    lateinit var recyclerView: RecyclerView
    lateinit var searchEditText: EditText

    var citys = ArrayList<AutoCompleteElement>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentOnBoardThirdBinding.inflate(inflater, container, false)

        recyclerView = onBoardThirdBinding.cityList
        searchEditText = onBoardThirdBinding.cityTextInput

        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                citys.clear()
                getAutoCompleteData(searchEditText.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }


        return onBoardThirdBinding.root
    }

    private fun getAutoCompleteData(q: String) {
        val apiService = AutoCompleteApiService.getInstance().create(AutoCompleteApiInterface::class.java)
        apiService.getCitys(q,"tr-tr").enqueue(object: Callback<List<AutoCompleteElement>> {

            override fun onFailure(call: Call<List<AutoCompleteElement>>, t: Throwable) {
                Log.d("TAG", "err $t")
            }

            override fun onResponse(call: Call<List<AutoCompleteElement>>, response: Response<List<AutoCompleteElement>>) {
                if (response.isSuccessful){
                    val adapter = AutoCompleteAdapter(citys,requireContext())
                    citys.addAll(response.body()!!)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                }
            }
        })
    }

}