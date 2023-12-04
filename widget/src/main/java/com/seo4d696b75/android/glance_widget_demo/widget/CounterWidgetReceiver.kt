package com.seo4d696b75.android.glance_widget_demo.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.seo4d696b75.android.glance_widget_demo.domain.CountRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class CounterWidgetReceiver : GlanceAppWidgetReceiver() {

    @Inject
    lateinit var repository: CountRepository

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val counterWidget by lazy {
        // unable to instantiate before injecting!
        CounterWidget(repository, coroutineScope)
    }

    override val glanceAppWidget: GlanceAppWidget
        get() = counterWidget

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        coroutineScope.launch {
            val manager = GlanceAppWidgetManager(context)
            appWidgetIds.map { appWidgetId ->
                val glanceId = manager.getGlanceIdBy(appWidgetId)
                launch {
                    counterWidget.increment(context, glanceId)
                }
            }.joinAll()
        }
    }
}
