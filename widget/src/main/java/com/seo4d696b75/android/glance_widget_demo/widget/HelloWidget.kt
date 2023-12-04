package com.seo4d696b75.android.glance_widget_demo.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import com.seo4d696b75.android.glance_widget_demo.widget.screen.HelloScreen
import com.seo4d696b75.android.glance_widget_demo.theme.WidgetTheme

class HelloWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            WidgetTheme {
                HelloScreen()
            }
        }
    }
}
