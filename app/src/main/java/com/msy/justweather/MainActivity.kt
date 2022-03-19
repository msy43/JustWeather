package com.msy.justweather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.msy.justweather.database.CityDatabase
import com.msy.justweather.database.CityEntity
import com.msy.justweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding : ActivityMainBinding

    private lateinit var goButton : Button
    private lateinit var stateImageSecond : ImageView
    private lateinit var stateImageThird : ImageView
    private lateinit var stateImageFourth : ImageView

    lateinit var cityEntity: CityEntity

    private var boardCount = 1

    private var firstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainActivityBinding.root

        goButton = mainActivityBinding.goOne
        stateImageSecond = mainActivityBinding.stateIconSecond
        stateImageThird = mainActivityBinding.stateIconThird
        stateImageFourth = mainActivityBinding.stateIconFourth

        goOnBoard()

        checkFirstTime()

        setContentView(view)
    }

    fun insertToDatabase(){

        val cityDatabase: CityDatabase = CityDatabase.getFavoritesDatabase(this)!!

        val sharedPreference =  mainActivityBinding.root.context.getSharedPreferences("city", Context.MODE_PRIVATE)
        val key = sharedPreference.getString("key","0")
        val cityName = sharedPreference.getString("cityName","0")
        val cityProvince = sharedPreference.getString("cityProvince","0")
        val cityCountry = sharedPreference.getString("cityCountry","0")

        cityEntity = CityEntity (0, key.toString(), cityName.toString(), cityProvince.toString(), cityCountry.toString())
        cityDatabase.cityDAO().insert(cityEntity)
        Log.v("TAG", cityDatabase.cityDAO().getAllCity().toString())
        Log.v("TAG", cityDatabase.cityDAO().selectRowGetKey(0).toString())
    }

    private fun checkFirstTime() {

        val sharedPreference =  getSharedPreferences("firstTimeCheck",Context.MODE_PRIVATE)
        firstTime = sharedPreference.getBoolean("isFirstTime", true)

        if (!firstTime) {
            toMainActivity2()
        }
    }

    private fun goOnBoard(){
        goButton.setOnClickListener {

            when (boardCount) {
                1 -> {
                    navigateFragments(R.id.action_onBoardFirst_to_onBoardSecond)
                    stateImageSecond.setImageResource(R.drawable.ic_baseline_circle_24)
                    goButton.text = "Okey !"
                    boardCount += 1
                }
                2 -> {
                    navigateFragments(R.id.action_onBoardSecond_to_onBoardThird)
                    stateImageThird.setImageResource(R.drawable.ic_baseline_circle_24)
                    goButton.text = "Add Cıty"
                    boardCount += 1
                }
                3 -> {
                    navigateFragments(R.id.action_onBoardThird_to_onBoardFourth)
                    stateImageFourth.setImageResource(R.drawable.ic_baseline_circle_24)
                    goButton.text = "Go"
                    boardCount += 1
                }
                4 -> {
                    insertToDatabase()
                    goMain()
                }
            }
        }
    }

    fun navigateFragments(id: Int) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(id)
    }


    fun toMainActivity2(){
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
        finish()
    }

    private fun goMain() {
        val sharedPreference = getSharedPreferences("firstTimeCheck", Context.MODE_PRIVATE)
        firstTime = false
        val editor = sharedPreference!!.edit()
        editor.putBoolean("isFirstTime", firstTime)
        editor.apply()

        toMainActivity2()
    }

    override fun onBackPressed() {
        when (boardCount) {
            4 -> {
                stateImageFourth.setImageResource(R.drawable.ic_outline_circle_24)
                goButton.text = "Add City"
                boardCount -= 1
            }

            3 -> {
                stateImageThird.setImageResource(R.drawable.ic_outline_circle_24)
                goButton.text = "Ok !"
                boardCount -= 1
            }
            2 -> {
                stateImageSecond.setImageResource(R.drawable.ic_outline_circle_24)
                goButton.text = "Next !"
                boardCount -= 1
            }
        }

        super.onBackPressed()
    }
}