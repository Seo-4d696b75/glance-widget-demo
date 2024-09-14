@file:Suppress("TestFunctionName")

package com.seo4d696b75.android.glance_widget_demo.widget

import android.content.Context
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.LocalContext
import androidx.glance.appwidget.testing.unit.runGlanceAppWidgetUnitTest
import androidx.glance.testing.unit.hasTestTag
import androidx.glance.testing.unit.hasText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.seo4d696b75.android.glance_widget_demo.theme.WidgetTheme
import com.seo4d696b75.android.glance_widget_demo.widget.screen.CounterScreen
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [34])
class CounterWidgetTest {
    @Test
    fun CounterScreen_idle() = runGlanceAppWidgetUnitTest {
        val context: Context = ApplicationProvider.getApplicationContext()
        setAppWidgetSize(DpSize(100.dp, 100.dp))
        provideComposable {
            CompositionLocalProvider(
                // Note: glanceString() requires the context
                LocalContext provides context
            ) {
                WidgetTheme {
                    CounterScreen(
                        count = 10,
                        isLoading = false,
                        onIncrement = { },
                        onDecrement = { },
                    )
                }
            }
        }

        onNode(hasText("10")).assertExists()
    }

    @Test
    fun CounterScreen_loading() = runGlanceAppWidgetUnitTest {
        val context: Context = ApplicationProvider.getApplicationContext()
        setAppWidgetSize(DpSize(100.dp, 100.dp))
        provideComposable {
            CompositionLocalProvider(
                // Note: glanceString() requires the context
                LocalContext provides context
            ) {
                WidgetTheme {
                    CounterScreen(
                        count = 10,
                        isLoading = true,
                        onIncrement = { },
                        onDecrement = { },
                    )
                }
            }
        }

        onNode(hasText("10")).assertDoesNotExist()
        onNode(hasTestTag("counter_loading")).assertExists()
    }
}
