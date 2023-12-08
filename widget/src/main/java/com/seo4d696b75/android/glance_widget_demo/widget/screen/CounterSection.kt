package com.seo4d696b75.android.glance_widget_demo.widget.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.seo4d696b75.android.glance_widget_demo.widget.R

@Composable
fun CounterSection(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: GlanceModifier = GlanceModifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CounterIconButton(
            iconProvider = ImageProvider(R.drawable.ic_arrow_down),
            contentDescription = glanceString(R.string.widget_decrement_label),
            key = "decrement",
            onClick = onDecrement,
            modifier = GlanceModifier.size(32.dp),
        )
        Spacer(
            modifier = GlanceModifier.width(8.dp),
        )
        Text(
            text = count.toString(),
            style = TextStyle(
                color = GlanceTheme.colors.onSurface,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            ),
            modifier = GlanceModifier.width(40.dp)
        )
        Spacer(
            modifier = GlanceModifier.width(8.dp),
        )
        CounterIconButton(
            iconProvider = ImageProvider(R.drawable.ic_arrow_up),
            contentDescription = glanceString(R.string.widget_increment_label),
            key = "increment",
            onClick = onIncrement,
            modifier = GlanceModifier.size(32.dp),
        )
    }
}
