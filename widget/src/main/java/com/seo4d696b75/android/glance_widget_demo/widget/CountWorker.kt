package com.seo4d696b75.android.glance_widget_demo.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.seo4d696b75.android.glance_widget_demo.domain.CountRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltWorker
class CountWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val repository: CountRepository,
) : CoroutineWorker(context, params) {
    override suspend fun doWork() = withContext(Dispatchers.IO) {
        runCatching {
            val widget = CounterWidget(repository)
            val appWidgetIds =
                params.inputData.getIntArray(KEY_APP_WIDGET_IDS) ?: throw IllegalArgumentException()
            val operation = when (params.inputData.getString(KEY_OPERATION)) {
                OPERATION_INCREMENT -> widget::increment
                OPERATION_DECREMENT -> widget::decrement
                else -> throw IllegalArgumentException()
            }
            val glanceAppWidgetManager = GlanceAppWidgetManager(context)

            appWidgetIds
                .map { glanceAppWidgetManager.getGlanceIdBy(it) }
                .map { launch { operation(context, it) } }
                .joinAll()

            Result.success()
        }.getOrElse { Result.failure() }
    }

    companion object {
        const val KEY_APP_WIDGET_IDS = "key_app_widget_ids"
        const val KEY_OPERATION = "key_operation"
        const val OPERATION_INCREMENT = "operation_increment"
        const val OPERATION_DECREMENT = "operation_decrement"

        fun requestIncrement(
            context: Context,
            appWidgetIds: IntArray,
        ) {
            val request = OneTimeWorkRequestBuilder<CountWorker>()
                .setInputData(
                    workDataOf(
                        KEY_APP_WIDGET_IDS to appWidgetIds,
                        KEY_OPERATION to OPERATION_INCREMENT,
                    )
                )
                .build()
            WorkManager.getInstance(context).enqueue(request)
        }

        fun requestDecrement(
            context: Context,
            appWidgetIds: IntArray,
        ) {
            val request = OneTimeWorkRequestBuilder<CountWorker>()
                .setInputData(
                    workDataOf(
                        KEY_APP_WIDGET_IDS to appWidgetIds,
                        KEY_OPERATION to OPERATION_DECREMENT,
                    )
                )
                .build()
            WorkManager.getInstance(context).enqueue(request)
        }
    }
}
