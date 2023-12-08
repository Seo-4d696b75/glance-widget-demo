package com.seo4d696b75.android.glance_widget_demo.widget.screen

import androidx.compose.runtime.Composable
import androidx.glance.ColorFilter
import androidx.glance.ExperimentalGlanceApi
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import com.seo4d696b75.android.glance_widget_demo.widget.R

@OptIn(ExperimentalGlanceApi::class)
@Composable
fun CounterIconButton(
    modifier: GlanceModifier = GlanceModifier,
    iconProvider: ImageProvider,
    contentDescription: String,
    key: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.clickable(
            key = key,
            block = onClick,
        ),
    ) {
        Image(
            provider = ImageProvider(R.drawable.button_background),
            contentDescription = null,
            colorFilter = ColorFilter.tint(GlanceTheme.colors.primary),
            modifier = GlanceModifier.fillMaxSize(),
        )
        Image(
            provider = iconProvider,
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimary),
            modifier = GlanceModifier.fillMaxSize(),
        )
    }
}