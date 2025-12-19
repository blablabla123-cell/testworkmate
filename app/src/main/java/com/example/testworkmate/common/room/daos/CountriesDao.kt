package com.example.testworkmate.common.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.testworkmate.common.data.model.CountryDto

@Dao
interface CountriesDao {
    @Query("SELECT name, flag, flagPictureUrl, flagContentDescription, continent, capital, currency, language, cachedAt FROM countries")
    suspend fun fetchCountries(): List<CountryDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun storeCountries(countries: List<CountryDto>)

    @Query("SELECT name, flag, flagPictureUrl, flagContentDescription, continent, capital, currency, language, cachedAt FROM countries WHERE name = :name")
    suspend fun fetchCountryDetailsByName(name: String): CountryDto

    @Upsert()
    suspend fun upsertCountryDetails(country: CountryDto)

    @Query("DELETE FROM countries")
    suspend fun clearCache()
}