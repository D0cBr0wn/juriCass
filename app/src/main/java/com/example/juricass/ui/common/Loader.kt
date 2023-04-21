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




@Preview(showBackground = true, showSystemUi = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun LoaderAndErrorDisplayerPreview() {
    JuriCassTheme() {
        LoaderAndErrorDisplayer(false, "error")
    }
}