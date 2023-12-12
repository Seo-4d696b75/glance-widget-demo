package com.seo4d696b75.android.glance_widget_demo.ui.configure

import androidx.compose.runtime.Immutable

@Immutable
data class ConfigureUiState(
    val input: String,
    val count: Int,
    val isError: Boolean,
)
