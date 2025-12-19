package com.example.testworkmate.details.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.testworkmate.R

@Composable
fun CountryDetailTextField(
    label: Int,
    value: String,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Text(
        stringResource(label), style = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.primary
        )
    )

    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors().copy(
            disabledTextColor = MaterialTheme.colorScheme.outline,
        ),
        shape = MaterialTheme.shapes.medium,
        visualTransformation = VisualTransformation.None,
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        value = value,
        onValueChange = {},
        trailingIcon = trailingIcon,
    )
}


@Preview
@Composable
fun CountryDetailTextFieldPreview() {
    CountryDetailTextField(R.string.capital, "Some value")
}
