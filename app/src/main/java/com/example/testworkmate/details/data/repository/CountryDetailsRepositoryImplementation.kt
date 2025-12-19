package com.example.testworkmate.details.data.repository

import com.example.testworkmate.common.CoreConstants
import com.example.testworkmate.common.data.model.CountryDto
import com.example.testworkmate.common.room.daos.CountriesDao
import com.example.testworkmate.details.data.data_source.CountryDetailsApi
import com.example.testworkmate.details.domain.repository.CountryDetailsRepository

class CountryDetailsRepositoryImplementation(
    val api: CountryDetailsApi,
    val dao: CountriesDao,
) : CountryDetailsRepository {
    override suspend fun fetchCountryDetails(
        countryName: String,
    ): CountryDto? {

        val now = System.currentTimeMillis()

        val cachedCountryDetails = dao.fetchCountryDetailsByName(countryName)

        val cachedAt = cachedCountryDetails.cachedAt

        val isUpdated =
            cachedCountryDetails.capital != null || cachedCountryDetails.currency != null || cachedCountryDetails.language != null || cachedCountryDetails.flagContentDescription != null || cachedCountryDetails.flagPictureUrl != null
        if (isUpdated && !CoreConstants.isCacheExpired(cachedAt)
        ) {
            return cachedCountryDetails
        } else {
            val countries = api.fetchCountryDetails(countryName)

            if (countries.isNotEmpty()) {
                val countryDetails = countries.first()
                val country = CountryDto(
                    countryDetails.name.official,
                    countryDetails.flag,
                    countryDetails.flags.png,
                    countryDetails.flags.alt,
                    countryDetails.capital.joinToString(", "),
                    countryDetails.continents.joinToString(", "),
                    countryDetails.languages.values.joinToString(", "),
                    countryDetails.currencies.values.joinToString(", ") {
                        "${it.name} (${it.symbol})"
                    },
                    now,
                );

                dao.upsertCountryDetails(country)

                return country
            } else {
                return null
            }
        }
    }
}