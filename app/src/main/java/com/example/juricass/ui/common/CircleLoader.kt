package com.example.juricass.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.juricass.R
import com.example.juricass.ui.theme.JuriCassTheme


@Composable
fun CircleLoader(isLoading: Boolean = false, error: String? = null) {
    if(isLoading || error != null) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().testTag("circleLoader")
        ) {
            if (error == null) {
                LoaderCircle(true)
            } else {
                ErrorDisplayer(error)
            }
        }
    }
}

@Composable
fun LoaderCircle(isLoading: Boolean) {
    if(isLoading) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondary,//Color.Cyan,//MaterialTheme.colors.secondary,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.loader_size))
        )
    } else {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.loader_size)))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun CircleLoaderPreview() {
    JuriCassTheme() {
        CircleLoader(isLoading = true)
    }
}