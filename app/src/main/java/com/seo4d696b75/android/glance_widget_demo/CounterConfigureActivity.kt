package com.seo4d696b75.android.glance_widget_demo

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.seo4d696b75.android.glance_widget_demo.ui.configure.ConfigureScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CounterConfigureActivity : ComponentActivity() {

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
                onCompleted = {
                    setResult(
                        RESULT_OK,
                        Intent().apply {
                            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                        }
                    )
                    finish()
                },
            )
        }
    }
}
