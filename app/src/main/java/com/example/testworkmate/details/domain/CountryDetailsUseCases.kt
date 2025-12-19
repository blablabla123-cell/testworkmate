package com.example.testworkmate.details.domain

import com.example.testworkmate.details.domain.usecases.FetchCountryDetailsUseCase
import javax.inject.Inject

data class CountryDetailsUseCases @Inject constructor (
    val fetchCountryDetailsUseCase: FetchCountryDetailsUseCase
)