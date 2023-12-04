package com.seo4d696b75.android.glance_widget_demo.theme

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.glance.GlanceTheme
import androidx.glance.material3.ColorProviders

@Composable
fun WidgetTheme(
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colors =
        if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            GlanceTheme.colors
        } else {
            ColorProviders(
                light = LightColorScheme,
                dark = DarkColorScheme,
            )
        }

    GlanceTheme(colors) {
        content()
    }
}
