package com.example.testworkmate.countries.domain

import com.example.testworkmate.countries.domain.usecases.FetchCountriesUseCase
import javax.inject.Inject

data class CountriesUseCases @Inject constructor(
    val fetchCountriesUseCase: FetchCountriesUseCase
)