package com.seo4d696b75.android.glance_widget_demo.widget.screen

import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.semantics.semantics
import androidx.glance.semantics.testTag

@Composable
fun LoadingSection(
    modifier: GlanceModifier = GlanceModifier,
) {
    CircularProgressIndicator(
        color = GlanceTheme.colors.primary,
        modifier = modifier.semantics {
            testTag = "counter_loading"
        },
    )
}
