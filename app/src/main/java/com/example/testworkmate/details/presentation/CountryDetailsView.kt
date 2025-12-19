package com.example.testworkmate.details.presentation

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testworkmate.common.CoreConstants
import com.example.testworkmate.common.presentation.components.CoreScaffold
import com.example.testworkmate.common.presentation.components.CoreTopAppBar
import com.example.testworkmate.common.presentation.views.ErrorView
import com.example.testworkmate.common.presentation.views.LoadingView
import com.example.testworkmate.details.presentation.view_model.CountryDetailsEvent
import com.example.testworkmate.details.presentation.view_model.CountryDetailsViewModel

@Composable
fun CountryDetailsView(
    navController: NavController,
    countryName: String,
    viewModel: CountryDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state

    LaunchedEffect(Unit) {
        viewModel.onEvent(CountryDetailsEvent.Init(countryName))
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onEvent(CountryDetailsEvent.Dispose)
        }
    }

    CoreScaffold(
        topBar = {
            CoreTopAppBar(
                isLoading = false,
                pop = {
                    navController.popBackStack()
                },
                actions = mutableListOf(
                    {
                        IconButton(onClick = {
                            viewModel.onEvent(CountryDetailsEvent.Init(countryName))
                        }) {
                            CoreConstants.ShowCoreIcon(CoreConstants.CoreIcon.UPDATE)
                        }
                    }
                )
            )
        }
    ) {
        when {
            state.loadingMessage != null -> {
                LoadingView(state.loadingMessage!!) { navController.popBackStack() }
            }

            state.error != null -> {
                ErrorView(state.error!!, { navController.popBackStack() }) {
                    viewModel.onEvent(CountryDetailsEvent.Init(countryName))
                }
            }

            else -> {
                CountryDetailsContentView(state.countryDetails) {
                    viewModel.onEvent(CountryDetailsEvent.Init(countryName))
                }
            }
        }
    }
}