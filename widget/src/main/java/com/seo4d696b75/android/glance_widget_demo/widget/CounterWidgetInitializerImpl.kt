package com.seo4d696b75.android.glance_widget_demo.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import com.seo4d696b75.android.glance_widget_demo.domain.CountRepository
import com.seo4d696b75.android.glance_widget_demo.domain.CounterWidgetInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class CounterWidgetInitializerImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val repository: CountRepository,
) : CounterWidgetInitializer {
    override suspend fun invoke(appWidgetId: Int, count: Int) {
        val widget = CounterWidget(repository)
        val glanceAppWidgetManager = GlanceAppWidgetManager(context)
        val glanceId = glanceAppWidgetManager.getGlanceIdBy(appWidgetId)
        widget.initialize(context, glanceId, count)
    }
}

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface CounterWidgetInitializerModule {
    @Binds
    fun bindCounterWidgetInitializer(impl: CounterWidgetInitializerImpl): CounterWidgetInitializer
}
