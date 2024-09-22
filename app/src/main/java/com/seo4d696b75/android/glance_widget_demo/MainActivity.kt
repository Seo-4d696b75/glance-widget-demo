package com.seo4d696b75.android.glance_widget_demo

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.seo4d696b75.android.glance_widget_demo.ui.home.HomeScreen
import com.seo4d696b75.android.glance_widget_demo.widget.CounterWidgetReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(
                addWidget = ::addWidget,
            )
        }
    }

    private fun addWidget(configure: Boolean) {
        val manager = AppWidgetManager.getInstance(this)
        if (manager.isRequestPinAppWidgetSupported) {
            val provider = ComponentName(this, CounterWidgetReceiver::class.java)
            val callback = if (configure) {
                PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, CounterConfigureActivity::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE,
                )
            } else {
                null
            }
            manager.requestPinAppWidget(provider, null, callback)
        }
    }
}
