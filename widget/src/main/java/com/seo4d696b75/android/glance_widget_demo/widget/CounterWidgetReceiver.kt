package com.seo4d696b75.android.glance_widget_demo.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.seo4d696b75.android.glance_widget_demo.domain.CountRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CounterWidgetReceiver : GlanceAppWidgetReceiver() {

    @Inject
    lateinit var repository: CountRepository

    private val counterWidget by lazy {
        // unable to instantiate before injecting!
        CounterWidget(repository)
    }

    override val glanceAppWidget: GlanceAppWidget
        get() = counterWidget

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        CountWorker.requestIncrement(context, appWidgetIds)
    }
}
