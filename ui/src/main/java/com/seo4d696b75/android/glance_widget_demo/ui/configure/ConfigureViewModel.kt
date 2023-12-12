package com.seo4d696b75.android.glance_widget_demo.ui.configure

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ConfigureViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ConfigureUiState(
            input = "1",
            count = 1,
            isError = false,
        )
    )

    val uiState = _uiState.asStateFlow()

    fun onInputChanged(text: String) {
        val value = text.toIntOrNull()
        _uiState.update {
            if (value != null && value in 1..99) {
                ConfigureUiState(
                    input = text,
                    count = value,
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
}
