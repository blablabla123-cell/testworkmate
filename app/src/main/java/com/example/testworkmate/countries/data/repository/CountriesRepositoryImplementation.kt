package com.example.testworkmate.countries.data.repository

import com.example.testworkmate.common.CoreConstants
import com.example.testworkmate.common.data.model.CountryDto
import com.example.testworkmate.countries.domain.repository.CountriesRepository
import com.example.testworkmate.common.room.daos.CountriesDao
import com.example.testworkmate.countries.data.data_source.CountriesApi
import okhttp3.internal.toImmutableList

class CountriesRepositoryImplementation(
    val api: CountriesApi,
    val dao: CountriesDao,
) : CountriesRepository {
    override suspend fun fetchCountries(): List<CountryDto> {

        val cachedCountries = dao.fetchCountries()

        val now = System.currentTimeMillis()

        val cachedAt = cachedCountries.firstOrNull()?.cachedAt

        if (cachedAt != null && !CoreConstants.isCacheExpired(cachedAt)) {
            return cachedCountries
        } else {
            val countries = api.fetchCountries().map { country ->
                CountryDto(
                    country.name.official,
                    country.flag,
                    cachedAt = now,
                )
            }.toImmutableList()
            dao.storeCountries(countries)
            return countries
        }
    }
}