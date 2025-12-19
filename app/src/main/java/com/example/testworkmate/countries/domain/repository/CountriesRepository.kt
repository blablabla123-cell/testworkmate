package com.example.testworkmate.countries.domain.repository

import com.example.testworkmate.common.data.model.CountryDto

interface CountriesRepository {
    suspend fun fetchCountries(): List<CountryDto>
}