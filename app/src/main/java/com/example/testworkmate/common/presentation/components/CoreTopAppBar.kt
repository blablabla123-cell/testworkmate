package com.example.testworkmate.common.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.testworkmate.common.CoreConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoreTopAppBar(
    title: String? = null,
    pop: (() -> Unit)? = null,
    // MAX 2
    actions: List<@Composable () -> Unit> = emptyList(),
    additionalActions: Map<String, () -> Unit>? = null,
    isLoading: Boolean,
) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { if (title != null)  {
            Text(title)
        } },
        navigationIcon = {
            if (pop != null) {
                IconButton(pop, enabled = !isLoading) {
                    CoreConstants.ShowCoreIcon(CoreConstants.CoreIcon.BACK)
                }
            }
        },
        actions = {
            actions.forEach { entry ->
                entry()
            }
            if (additionalActions != null) {
                Box {
                    IconButton({ showMenu = true }, enabled = !isLoading) {
                        CoreConstants.ShowCoreIcon(CoreConstants.CoreIcon.MORE)
                    }
                    DropdownMenu(showMenu, { showMenu = false }) {
                        additionalActions.forEach { action ->
                            DropdownMenuItem(
                                text = { Text(action.key) },
                                {
                                    action.value()
                                    showMenu = false
                                },
                                enabled = !isLoading,
                            )
                        }
                    }
                }
            }
        }
    )
}

