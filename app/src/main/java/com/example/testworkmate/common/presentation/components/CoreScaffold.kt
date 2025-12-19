package com.example.testworkmate.common.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.testworkmate.R

@Composable
fun CoreScaffold(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit)? = null,
    floatingActionButton: @Composable (() -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if(bottomBar != null) {
                bottomBar()
            }
        },
        floatingActionButton = {
            if (floatingActionButton != null) {
                floatingActionButton()
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            if (topBar != null) {
                topBar()
            }
        },
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(dimensionResource(R.dimen.regular))
        ) {
            content()
        }
    }
}
