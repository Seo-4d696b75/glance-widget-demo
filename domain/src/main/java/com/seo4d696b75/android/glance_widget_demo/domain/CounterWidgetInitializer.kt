package com.seo4d696b75.android.glance_widget_demo.domain

interface CounterWidgetInitializer {
    suspend operator fun invoke(appWidgetId: Int, count: Int)
}