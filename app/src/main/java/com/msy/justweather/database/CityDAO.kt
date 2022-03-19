package com.msy.justweather.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDAO {

    @Insert
    fun insert(citys: CityEntity)

    @Query("DELETE FROM citydatabase WHERE `id` = :key")
    fun deleteWithId(key: Int)

    @Query("SELECT EXISTS (SELECT 1 FROM citydatabase WHERE `id`= :key)")
    fun checkisAdded(key: Int) : Boolean

    @Query("SELECT `cityId` FROM citydatabase LIMIT 1 OFFSET :row")
    fun selectRowGetKey(row: Int) : Int

    @Query("SELECT `cityName` FROM citydatabase WHERE `cityId`= :key")
    fun selectKeyGetName(key: String) : String

    @Query("SELECT COUNT(cityId) FROM citydatabase")
    fun getCount(): Int

    @Query("DELETE FROM citydatabase ")
    fun delete()

    @Query("UPDATE citydatabase SET id = 0")
    fun reset()

    @Query("SELECT*FROM citydatabase")
    fun getAllCity(): List<CityEntity>

}