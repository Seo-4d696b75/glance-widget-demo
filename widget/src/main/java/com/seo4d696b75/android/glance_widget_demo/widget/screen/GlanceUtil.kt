package com.seo4d696b75.android.glance_widget_demo.widget.screen

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.glance.LocalContext

@Composable
fun glanceString(@StringRes id: Int): String {
    return LocalContext.current.getString(id)
}
