package com.seo4d696b75.android.glance_widget_demo.domain

interface CountRepository {
    suspend fun increment(current: Int): Int
    suspend fun decrement(current: Int): Int
}
