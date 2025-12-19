package com.example.testworkmate.common.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.testworkmate.R
import com.example.testworkmate.common.CoreConstants
import com.example.testworkmate.common.presentation.components.CoreScaffold
import kotlinx.coroutines.delay

@Composable
fun LoadingView(
    message: Int,
    pop: () -> Unit,
) {
    var buttonVisible by mutableStateOf(false)


    LaunchedEffect(Unit) {
        delay(3000) // Wait 3 seconds
        buttonVisible = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(message))
        CircularProgressIndicator()
        if (buttonVisible)
            TextButton(
                onClick = {
                    pop.invoke()
                }
            ) {
                Text(stringResource(R.string.pop))
            }
    }
}