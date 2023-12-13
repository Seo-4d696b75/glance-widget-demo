package com.seo4d696b75.android.glance_widget_demo.domain

interface CounterWidgetMediator {
    suspend fun getCount(appWidgetId: Int): Int
    suspend fun setCount(appWidgetId: Int, count: Int)
}