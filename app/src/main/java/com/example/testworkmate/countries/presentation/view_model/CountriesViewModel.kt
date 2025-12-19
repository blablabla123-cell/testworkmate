package com.example.testworkmate.countries.presentation.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testworkmate.R
import com.example.testworkmate.common.domain.CommonUseCases
import com.example.testworkmate.common.domain.usecases.ClearLocalStorageUseCase
import com.example.testworkmate.common.utils.Resource
import com.example.testworkmate.common.utils.ViewModelEventHandler
import com.example.testworkmate.countries.domain.CountriesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    val useCases: CountriesUseCases,
    val commonUseCases: CommonUseCases,
) : ViewModel(), ViewModelEventHandler<CountriesEvent> {

    private val _state = mutableStateOf(CountriesState())
    val state = _state

    override fun onEvent(event: CountriesEvent) {
        when (event) {
            is CountriesEvent.Init -> init()
            is CountriesEvent.Dispose -> {
                _state.value = CountriesState()
            }
            is CountriesEvent.ClearCache -> clearCache()

        }
    }

    private fun init() {
        useCases.fetchCountriesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        loadingMessage = R.string.loading_countries,
                        error = null,
                        countries = emptyList(),
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        loadingMessage = null,
                        error = result.message ?: R.string.unexpected_error.toString(),
                        countries = emptyList(),
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        loadingMessage = null,
                        error = result.message,
                        countries = result.data ?: emptyList(),
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun clearCache() {
        commonUseCases.clearLocalStorageUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        loadingMessage = R.string.clear_cache,
                        error = null,
                        countries = emptyList(),
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        loadingMessage = null,
                        error = result.message ?: R.string.unexpected_error.toString(),
                        countries = emptyList(),
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        loadingMessage = null,
                        error = result.message,
                        countries = emptyList(),
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}

