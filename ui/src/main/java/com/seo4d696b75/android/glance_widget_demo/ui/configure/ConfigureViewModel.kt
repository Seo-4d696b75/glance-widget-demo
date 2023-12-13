package com.seo4d696b75.android.glance_widget_demo.ui.configure

import android.appwidget.AppWidgetManager
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seo4d696b75.android.glance_widget_demo.domain.CountRepository
import com.seo4d696b75.android.glance_widget_demo.domain.CounterWidgetMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigureViewModel @Inject constructor(
    private val repository: CountRepository,
    private val mediator: CounterWidgetMediator,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val appWidgetId: Int =
        requireNotNull(savedStateHandle[AppWidgetManager.EXTRA_APPWIDGET_ID])

    private var count = 1

    private val _uiState = MutableStateFlow(
        ConfigureUiState(
            input = "1",
            isError = false,
            hasCompleted = false,
        )
    )

    init {
        viewModelScope.launch {
            val initialCount = mediator.getCount(appWidgetId)
            count = initialCount
            _uiState.update {
                ConfigureUiState(
                    input = initialCount.toString(),
                    isError = false,
                    hasCompleted = false,
                )
            }
        }
    }

    val uiState = _uiState.asStateFlow()

    fun onInputChanged(text: String) {
        val value = text.toIntOrNull()
        _uiState.update {
            if (value != null && repository.validate(value)) {
                count = value
                it.copy(
                    input = text,
                    isError = false,
                )
            } else {
                it.copy(
                    input = text,
                    isError = true,
                )
            }
        }
    }

    fun complete() {
        if (_uiState.value.isError) return
        viewModelScope.launch {
            mediator.setCount(appWidgetId, count)
            _uiState.update {
                it.copy(hasCompleted = true)
            }
        }
    }
}
