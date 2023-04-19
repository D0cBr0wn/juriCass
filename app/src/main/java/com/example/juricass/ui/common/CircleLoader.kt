package com.example.juricass.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.juricass.R


@Composable
fun CircleLoader(isLoading: Boolean = false, error: String? = null) {
    Column(
        //verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.app_xsmall))
    ) {
        if(error == null) {
            LoaderCircle(isLoading)
        } else {
            ErrorDisplayer(error)
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