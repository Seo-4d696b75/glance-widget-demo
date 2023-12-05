package com.seo4d696b75.android.glance_widget_demo.widget

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.currentState
import com.seo4d696b75.android.glance_widget_demo.domain.CountRepository
import com.seo4d696b75.android.glance_widget_demo.theme.WidgetTheme
import com.seo4d696b75.android.glance_widget_demo.widget.screen.CounterScreen

class CounterWidget constructor(
    private val repository: CountRepository,
) : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val appWidgetId = GlanceAppWidgetManager(context).getAppWidgetId(id)
        provideContent {
            val pref: Preferences = currentState()
            val state = pref[PREF_KEY_COUNT] ?: 0
            WidgetTheme {
                CounterScreen(
                    count = state,
                    onIncrement = {
                        CountWorker.requestIncrement(context, intArrayOf(appWidgetId))
                    },
                    onDecrement = {
                        CountWorker.requestDecrement(context, intArrayOf(appWidgetId))
                    },
                )
            }
        }
    }

    suspend fun increment(context: Context, glanceId: GlanceId) {
        updateAppWidgetState(context, glanceId) {
            val current = it[PREF_KEY_COUNT] ?: 0
            val next = repository.increment(current)
            it[PREF_KEY_COUNT] = next
        }
        update(context, glanceId)
    }

    suspend fun decrement(context: Context, glanceId: GlanceId) {
        updateAppWidgetState(context, glanceId) {
            val current = it[PREF_KEY_COUNT] ?: 0
            val next = repository.decrement(current)
            it[PREF_KEY_COUNT] = next
        }
        update(context, glanceId)
    }

    companion object {
        private val PREF_KEY_COUNT = intPreferencesKey("pref_key_count")
    }
}
