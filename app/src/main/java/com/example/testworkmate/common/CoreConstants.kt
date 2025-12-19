package com.example.testworkmate.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.testworkmate.R

object CoreConstants {
    const val SERVER_URI = "https://restcountries.com/v3.1/"

    const val FIELDS_QUERY = "fields"

    private const val CACHE_TTL_MS = 3 * 60 * 60 * 1000L

    const val COUNTRIES_API = "all"
    const val COUNTRY_DETAILS_API = "name"

    const val DATABASE_NAME = "workmate"

    sealed class Destinations (val destination: String) {
        data object Main: Destinations("main")
        data object Details: Destinations("details")
    }

    enum class CoreIcon(val vector: ImageVector, val resource: Int) {
        ATTENTION(Icons.Default.ErrorOutline, R.string.error_content_description),
        MORE(Icons.Default.MoreVert, R.string.more_content_description),
        UPDATE(Icons.Default.Update, R.string.update_content_description),
        BACK(Icons.AutoMirrored.Default.ArrowBackIos, R.string.arrow_back_content_description)
    }

    @Composable
    fun ShowCoreIcon(icon: CoreIcon, modifier: Modifier = Modifier) {
        Icon(icon.vector,
            stringResource(icon.resource),
            modifier
        )
    }

    fun isCacheExpired(cachedAt: Long): Boolean {
        val now = System.currentTimeMillis()
        return now - cachedAt > CACHE_TTL_MS
    }
}