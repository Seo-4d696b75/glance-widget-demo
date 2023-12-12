package com.seo4d696b75.android.glance_widget_demo

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.lifecycle.lifecycleScope
import com.seo4d696b75.android.glance_widget_demo.domain.CountRepository
import com.seo4d696b75.android.glance_widget_demo.ui.configure.ConfigureScreen
import com.seo4d696b75.android.glance_widget_demo.widget.CounterWidget
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CounterConfigureActivity : ComponentActivity() {

    @Inject
    lateinit var repository: CountRepository

    private val appWidgetId: Int by lazy {
        intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID,
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setResult(
            RESULT_CANCELED,
            Intent().apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            },
        )

        setContent {
            ConfigureScreen(
                onCompleted = { count ->
                    lifecycleScope.launch {
                        val widget = CounterWidget(repository)
                        val context = this@CounterConfigureActivity
                        val glanceAppWidgetManager = GlanceAppWidgetManager(context)
                        val glanceId = glanceAppWidgetManager.getGlanceIdBy(appWidgetId)
                        widget.initialize(context, glanceId, count)
                        setResult(
                            RESULT_OK,
                            Intent().apply {
                                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                            }
                        )
                        finish()
                    }
                },
            )
        }
    }
}
