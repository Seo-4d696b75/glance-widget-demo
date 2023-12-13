package com.seo4d696b75.android.glance_widget_demo.widget

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.getAppWidgetState
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
            val isLoading = pref[PREF_KEY_IS_LOADING] ?: false
            WidgetTheme {
                CounterScreen(
                    isLoading = isLoading,
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
        val pref: Preferences = getAppWidgetState(context, glanceId)
        val isLoading = pref[PREF_KEY_IS_LOADING] ?: false
        val current = pref[PREF_KEY_COUNT] ?: 0
        if (isLoading || current >= 99) return
        updateAppWidgetState(context, glanceId) {
            it[PREF_KEY_IS_LOADING] = true
        }
        update(context, glanceId)
        val next = repository.increment(current)
        updateAppWidgetState(context, glanceId) {
            it[PREF_KEY_COUNT] = next
            it[PREF_KEY_IS_LOADING] = false
        }
        update(context, glanceId)
    }

    suspend fun decrement(context: Context, glanceId: GlanceId) {
        val pref: Preferences = getAppWidgetState(context, glanceId)
        val isLoading = pref[PREF_KEY_IS_LOADING] ?: false
        val current = pref[PREF_KEY_COUNT] ?: 0
        if (isLoading || current <= 1) return
        updateAppWidgetState(context, glanceId) {
            it[PREF_KEY_IS_LOADING] = true
        }
        update(context, glanceId)
        val next = repository.decrement(current)
        updateAppWidgetState(context, glanceId) {
            it[PREF_KEY_COUNT] = next
            it[PREF_KEY_IS_LOADING] = false
        }
        update(context, glanceId)
    }

    suspend fun getCount(context: Context, glanceId: GlanceId): Int {
        val pref: Preferences = getAppWidgetState(context, glanceId)
        return pref[PREF_KEY_COUNT] ?: 0
    }

    suspend fun setCount(context: Context, glanceId: GlanceId, count: Int) {
        updateAppWidgetState(context, glanceId) {
            it[PREF_KEY_COUNT] = count
            it[PREF_KEY_IS_LOADING] = false
        }
        update(context, glanceId)
    }

    companion object {
        private val PREF_KEY_COUNT = intPreferencesKey("pref_key_count")
        private val PREF_KEY_IS_LOADING = booleanPreferencesKey("pref_key_is_loading")
    }
}
