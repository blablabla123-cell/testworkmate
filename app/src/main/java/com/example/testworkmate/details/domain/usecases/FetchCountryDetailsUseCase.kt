package com.example.testworkmate.details.domain.usecases

import com.example.testworkmate.common.utils.Resource
import com.example.testworkmate.common.domain.model.Country
import com.example.testworkmate.details.domain.repository.CountryDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchCountryDetailsUseCase(
    val repository: CountryDetailsRepository
) {
    operator fun invoke(
        countryName: String
    ): Flow<Resource<Country>> = flow {
        try {
            emit(Resource.Loading())

            val countryDetails = repository.fetchCountryDetails(countryName)
            if (countryDetails != null) {

                emit(
                    Resource.Success(
                        Country(
                            countryDetails.name,
                            countryDetails.flag,
                            countryDetails.flagPictureUrl,
                            countryDetails.flagContentDescription,
                            countryDetails.capital,
                            countryDetails.continent,
                            countryDetails.language,
                            countryDetails.currency,
                        )
                    )
                )
            } else {
                emit(Resource.Error("Failed to fetch country details"))
            }

        } catch (exception: Exception) {
            emit(Resource.Error(exception.message ?: "Unexpected error occurred"))
        }
    }
}