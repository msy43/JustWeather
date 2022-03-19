package com.msy.justweather.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citydatabase")
data class CityEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "cityId")
    var cityId: String,

    @ColumnInfo(name = "cityName")
    var cityName: String,

    @ColumnInfo(name = "cityProvince")
    var cityProvince: String,

    @ColumnInfo(name = "cityCountry")
    var cityCountry: String

    )