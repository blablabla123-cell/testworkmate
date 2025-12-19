package com.example.testworkmate.countries.data.data_source

import com.example.testworkmate.common.CoreConstants.COUNTRIES_API
import com.example.testworkmate.common.CoreConstants.FIELDS_QUERY
import com.example.testworkmate.common.CoreConstants.SERVER_URI
import com.example.testworkmate.common.data.dto.CountryEntity
import retrofit2.http.GET

interface CountriesApi {
    @GET( COUNTRIES_API + "?${FIELDS_QUERY}=name,flag")
    suspend fun fetchCountries(): List<CountryEntity>
}