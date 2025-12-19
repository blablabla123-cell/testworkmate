package com.example.testworkmate.countries.domain.usecases

import com.example.testworkmate.common.utils.Resource
import com.example.testworkmate.countries.domain.repository.CountriesRepository
import com.example.testworkmate.common.domain.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchCountriesUseCase(
    val repository: CountriesRepository
) {
    operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading())

            val countries = repository.fetchCountries().map {
                Country(
                    it.name,
                    it.flag
                )
            }

            emit(Resource.Success(countries))
        } catch (exception: Exception) {
            emit(Resource.Error(exception.message ?: "Unexpected error occurred"))
        }
    }
}