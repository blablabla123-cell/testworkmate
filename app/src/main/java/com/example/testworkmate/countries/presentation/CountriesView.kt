import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testworkmate.R
import com.example.testworkmate.common.CoreConstants
import com.example.testworkmate.common.presentation.components.CoreListTile
import com.example.testworkmate.common.presentation.components.CoreScaffold
import com.example.testworkmate.common.presentation.components.CoreTopAppBar
import com.example.testworkmate.common.presentation.views.ErrorView
import com.example.testworkmate.common.presentation.views.LoadingView
import com.example.testworkmate.countries.presentation.CountriesContentView
import com.example.testworkmate.countries.presentation.view_model.CountriesEvent
import com.example.testworkmate.countries.presentation.view_model.CountriesViewModel


@Composable()
fun CountriesView(
    navController: NavController,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val state by viewModel.state

    LaunchedEffect(Unit) {
        viewModel.onEvent(CountriesEvent.Init)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onEvent(CountriesEvent.Dispose)
        }
    }

    CoreScaffold(
        topBar = {
            CoreTopAppBar(
                title = stringResource(R.string.countries),
                isLoading = state.loadingMessage != null,
                actions = mutableListOf(
                    {
                        IconButton(onClick = {
                            viewModel.onEvent(CountriesEvent.Init)
                        }) {
                            CoreConstants.ShowCoreIcon(CoreConstants.CoreIcon.UPDATE)
                        }
                    },
                ),
                additionalActions = mapOf(
                    stringResource(R.string.clear_cache) to {
                        viewModel.onEvent(CountriesEvent.ClearCache)
                    }
                )

            )
        },
    ) {

        when {
            state.loadingMessage != null -> {
                LoadingView(state.loadingMessage!!) {
                    navController.popBackStack()
                }
            }

            state.error != null -> {
                ErrorView(state.error!!) {
                    viewModel.onEvent(CountriesEvent.Init)
                }
            }

            else -> {
                CountriesContentView(
                    state.countries,
                    {
                        navController.navigate(
                            CoreConstants.Destinations.Details.destination + "/${it}"
                        )
                    },
                    { viewModel.onEvent(CountriesEvent.Init) })
            }
        }
    }
}