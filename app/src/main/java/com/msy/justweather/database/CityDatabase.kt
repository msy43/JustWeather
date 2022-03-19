package com.msy.justweather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 1)
abstract class CityDatabase : RoomDatabase() {

    abstract fun cityDAO() : CityDAO

    companion object {
        private var instance: CityDatabase? = null

        fun getFavoritesDatabase(context: Context): CityDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    CityDatabase::class.java,
                    "citydatabase.db"
                ).allowMainThreadQueries().build()
            }
            return instance
        }
    }

}