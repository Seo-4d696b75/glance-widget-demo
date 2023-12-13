package com.seo4d696b75.android.glance_widget_demo.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import com.seo4d696b75.android.glance_widget_demo.domain.CountRepository
import com.seo4d696b75.android.glance_widget_demo.domain.CounterWidgetMediator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class CounterWidgetMediatorImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    repository: CountRepository,
) : CounterWidgetMediator {

    private val glanceAppWidgetManager = GlanceAppWidgetManager(context)
    private val widget = CounterWidget(repository)

    override suspend fun getCount(appWidgetId: Int): Int {
        val glanceId = glanceAppWidgetManager.getGlanceIdBy(appWidgetId)
        return widget.getCount(context, glanceId)
    }

    override suspend fun setCount(appWidgetId: Int, count: Int) {
        val glanceId = glanceAppWidgetManager.getGlanceIdBy(appWidgetId)
        widget.setCount(context, glanceId, count)
    }
}

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface CounterWidgetMediatorModule {
    @Binds
    fun bindCounterWidgetMediator(impl: CounterWidgetMediatorImpl): CounterWidgetMediator
}
