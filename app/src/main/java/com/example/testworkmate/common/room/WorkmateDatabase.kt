package com.example.testworkmate.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testworkmate.common.data.model.CountryDto
import com.example.testworkmate.common.room.daos.CountriesDao

@Database(entities = [CountryDto::class], version = 1)
abstract class WorkmateDatabase: RoomDatabase() {
    abstract val countriesDao: CountriesDao
}