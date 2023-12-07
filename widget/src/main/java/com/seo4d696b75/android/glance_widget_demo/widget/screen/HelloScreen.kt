package com.seo4d696b75.android.glance_widget_demo.widget.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.seo4d696b75.android.glance_widget_demo.widget.R

@Composable
fun HelloScreen(
    modifier: GlanceModifier = GlanceModifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            provider = ImageProvider(R.drawable.widget_background),
            contentDescription = null,
            colorFilter = ColorFilter.tint(GlanceTheme.colors.surface),
            modifier = GlanceModifier.fillMaxSize(),
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = glanceString(id = R.string.widget_hello_message),
                style = TextStyle(
                    color = GlanceTheme.colors.onSurface,
                )
            )
        }
    }
}
