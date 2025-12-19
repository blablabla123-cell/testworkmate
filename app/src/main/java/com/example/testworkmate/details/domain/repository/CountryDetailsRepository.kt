package com.example.testworkmate.details.domain.repository

import com.example.testworkmate.common.data.model.CountryDto

interface CountryDetailsRepository {
    suspend fun fetchCountryDetails(countryName: String): CountryDto?
}