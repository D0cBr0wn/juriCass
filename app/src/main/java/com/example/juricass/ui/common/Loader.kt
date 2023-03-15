package com.example.juricass.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.juricass.ui.theme.JuriCassTheme
import com.example.juricass.R

@Composable
fun Loader(flag: Boolean) {
    if(flag) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondary,//Color.Cyan,//MaterialTheme.colors.secondary,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.loader_size))
        )
    } else {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.loader_size)))
    }
}

@Composable
fun ErrorDisplayer(error: String? = null) {
    if(error != null) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment  = Alignment.CenterVertically,
            modifier = Modifier
                .border(width = dimensionResource(id = R.dimen.border_width), color = colorResource(id = R.color.error))
        ) {
            val img =  painterResource(id = R.drawable.baseline_error_24)
            Icon(
                painter = img,
                contentDescription = null,
                tint = colorResource(id = R.color.error),
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.app_small))
            )
            Text(
                text = error.uppercase(),
                color = colorResource(id = R.color.error),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.app_small),
                        bottom = dimensionResource(id = R.dimen.app_small),
                        end = dimensionResource(id = R.dimen.app_small),
                        start = dimensionResource(R.dimen.border_width)
                    )
            )
        }

    }
}

@Composable
fun LoaderAndErrorDisplayer(flag: Boolean = false, error: String?) {
    Column(
        //verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.app_xsmall))
    ) {
        if(flag && error == null) {
            Loader(flag)
        }

        if(error != null) {
            ErrorDisplayer(error)
        }
    }
}


@Preview(showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ErrorPreview() {
    JuriCassTheme() {
        ErrorDisplayer("error")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun LoaderAndErrorDisplayerPreview() {
    JuriCassTheme() {
        LoaderAndErrorDisplayer(false, "error")
    }
}