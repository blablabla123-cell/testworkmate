package com.example.testworkmate.details.data.data_source

import com.example.testworkmate.common.CoreConstants
import com.example.testworkmate.common.data.dto.CountryEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryDetailsApi {
    @GET(CoreConstants.COUNTRY_DETAILS_API + "/{name}")
    suspend fun fetchCountryDetails(@Path("name") countryName: String): List<CountryEntity>
}