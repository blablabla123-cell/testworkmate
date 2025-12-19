package com.example.testworkmate.countries.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.testworkmate.R
import com.example.testworkmate.common.domain.model.Country
import com.example.testworkmate.common.presentation.components.CoreListTile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesContentView(
    countries: List<Country>,
    onNavigate: (String) -> Unit,
    onTryAgain: () -> Unit,
) {
        if (countries.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(stringResource(R.string.no_countries))

                TextButton(
                    onClick = {
                        onTryAgain.invoke()
                    }
                ) {
                    Text(stringResource(com.example.testworkmate.R.string.try_again))
                }
            }
        } else {
            LazyColumn() {
                items(countries) { country ->
                    print(country.flag)
                    CoreListTile(
                        {
                            Text(
                                country.flag,
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            )
                        },
                        {
                            Text(
                                country.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    ) {
                        onNavigate(country.name)
                    }
                }
            }

    }
}