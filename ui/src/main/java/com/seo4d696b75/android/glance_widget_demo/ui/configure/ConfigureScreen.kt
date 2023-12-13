package com.seo4d696b75.android.glance_widget_demo.ui.configure

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seo4d696b75.android.glance_widget_demo.theme.AppTheme
import com.seo4d696b75.android.glance_widget_demo.ui.R

@Composable
fun ConfigureScreen(
    onCompleted: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ConfigureViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.hasCompleted) {
        if (uiState.hasCompleted) {
            onCompleted()
        }
    }

    ConfigureScreen(
        input = uiState.input,
        isError = uiState.isError,
        onInputChanged = viewModel::onInputChanged,
        complete = viewModel::complete,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigureScreen(
    input: String,
    isError: Boolean,
    onInputChanged: (String) -> Unit,
    complete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AppTheme {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.configure_app_bar))
                    },
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.configure_message),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(
                        modifier = Modifier.height(12.dp),
                    )
                    OutlinedTextField(
                        value = input,
                        onValueChange = onInputChanged,
                        isError = isError,
                        label = {
                            Text(text = stringResource(id = R.string.configure_text_field_label))
                        },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { complete() },
                        ),
                        modifier = Modifier.width(120.dp),
                    )
                    Spacer(
                        modifier = Modifier.height(36.dp),
                    )
                    Button(
                        onClick = complete,
                        enabled = !isError,
                    ) {
                        Text(
                            text = stringResource(id = R.string.configure_complete_button_label),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ConfigureScreenPreview() {
    ConfigureScreen(
        input = "10",
        isError = false,
        onInputChanged = {},
        complete = {},
    )
}

@Preview
@Composable
private fun ConfigureScreenPreview_invalid() {
    ConfigureScreen(
        input = "zero",
        isError = true,
        onInputChanged = {},
        complete = {},
    )
}
