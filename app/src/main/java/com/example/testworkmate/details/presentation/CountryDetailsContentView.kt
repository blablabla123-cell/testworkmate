package com.example.testworkmate.details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.testworkmate.R
import com.example.testworkmate.common.domain.model.Country

@Composable
fun CountryDetailsContentView(
    countryDetails: Country?,
    onTryAgain: () -> Unit,
) {

        if (countryDetails != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    countryDetails.name,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.weight(3f),
                    maxLines = 2
                )

                AsyncImage(
                    model = countryDetails.flagPictureUrl,
                    contentDescription = countryDetails.flagContentDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.weight(1f)
                )

            }
            Spacer(Modifier.height(dimensionResource(R.dimen.large)))
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.regular))
            ) {
                if (countryDetails.continent != null)
                    CountryDetailTextField(R.string.continent, countryDetails.continent)
                if (countryDetails.capital != null)
                    CountryDetailTextField(R.string.capital, countryDetails.capital)
                if (countryDetails.language != null)
                    CountryDetailTextField(R.string.language, countryDetails.language)
                if (countryDetails.currency != null)
                    CountryDetailTextField(R.string.currency, countryDetails.currency)

                CountryDetailTextField(
                    R.string.other_details,
                    stringResource(R.string.emoji_flag),
                    {
                        Text(countryDetails.flag)
                    })
            }
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(stringResource(R.string.no_country_details))

                TextButton(
                    onClick = {
                        onTryAgain.invoke()
                    }
                ) {
                    Text(stringResource(com.example.testworkmate.R.string.try_again))
                }
            }
        }

}
