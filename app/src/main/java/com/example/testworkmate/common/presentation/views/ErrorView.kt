package com.example.testworkmate.common.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.testworkmate.R
import com.example.testworkmate.common.CoreConstants
import com.example.testworkmate.common.CoreConstants.ShowCoreIcon
import com.example.testworkmate.common.presentation.components.CoreScaffold

@Composable
fun ErrorView(
    error: String,
    pop: (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.small)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ShowCoreIcon(CoreConstants.CoreIcon.ATTENTION)
        Text(error)
        if (pop != null) {
            TextButton(
                onClick = {
                    pop.invoke()
                }
            ) {
                Text(stringResource(R.string.pop))
            }
        }
        TextButton(
            onClick = {
                onClick.invoke()
            }
        ) {
            Text(stringResource(R.string.try_again))
        }
    }
}