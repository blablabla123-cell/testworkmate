package com.example.testworkmate.common.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.testworkmate.R

@Composable
fun CoreListTile(
    leading: (@Composable () -> Unit)? = null,
    title: (@Composable () -> Unit)? = null,
    onTap: (() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.regular)
        ),
        modifier = Modifier
            .clickable(onClick = {
                onTap?.invoke()
            })
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.regular))
    ) {
        leading?.invoke()

        title?.invoke()
    }
    HorizontalDivider()
}

@Preview(showBackground = true)
@Composable
fun CoreListTilePreview() {
    CoreListTile(
        {
            Text("Leading")
        },
        {
            Text("Title")
        }
    )
}