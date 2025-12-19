package com.example.testworkmate.details.presentation.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testworkmate.R
import com.example.testworkmate.common.utils.Resource

import com.example.testworkmate.common.utils.ViewModelEventHandler
import com.example.testworkmate.details.domain.CountryDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    val useCases: CountryDetailsUseCases,
) : ViewModel(), ViewModelEventHandler<CountryDetailsEvent> {

    private val _state = mutableStateOf(CountryDetailsState())
    val state = _state


    override fun onEvent(event: CountryDetailsEvent) {
        when (event) {
            is CountryDetailsEvent.Init -> init(event)
            is CountryDetailsEvent.Dispose -> {
                _state.value = CountryDetailsState()
            }
        }
    }

    private fun init(event: CountryDetailsEvent.Init) {
        useCases.fetchCountryDetailsUseCase(event.countryName).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        countryDetails = null,
                        loadingMessage = R.string.loading_country_details,
                        error = null,
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        countryDetails = null,
                        loadingMessage = null,
                        error = result.message,
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        countryDetails = result.data,
                        loadingMessage = null,
                        error = null,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}

