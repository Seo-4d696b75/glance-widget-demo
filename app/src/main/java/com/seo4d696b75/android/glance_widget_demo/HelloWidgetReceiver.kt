package com.seo4d696b75.android.glance_widget_demo

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.seo4d696b75.android.glance_widget_demo.widget.HelloWidget

class HelloWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = HelloWidget()
}
