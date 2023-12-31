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
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.seo4d696b75.android.glance_widget_demo.widget.R

@Composable
fun CounterScreen(
    count: Int,
    isLoading: Boolean,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = glanceString(id = R.string.widget_counter_title),
                style = TextStyle(
                    color = GlanceTheme.colors.onSurface,
                )
            )
            Spacer(
                modifier = GlanceModifier.height(8.dp),
            )
            if (isLoading) {
                LoadingSection(
                    modifier = GlanceModifier.size(40.dp),
                )
            } else {
                CounterSection(
                    count = count,
                    onIncrement = onIncrement,
                    onDecrement = onDecrement,
                    modifier = GlanceModifier.height(40.dp),
                )
            }
        }
    }
}
