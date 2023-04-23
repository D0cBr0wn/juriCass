package com.example.juricass.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.juricass.ui.theme.JuriCassTheme


//@Composable
//fun Logo() {
//    Image(
//        painter = painterResource(id = R.drawable.ic_vivascan_logo),
//        contentDescription = stringResource(R.string.logo_descr),
//        modifier = Modifier,
//        colorFilter =  ColorFilter.tint(MaterialTheme.colors.onBackground)
//    )
//}



@Composable
fun ErrorDisplayer(error: String? = null, reverse: Boolean = false) {
    if(error != null) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment  = Alignment.CenterVertically,
            modifier = Modifier
                .border(width = dimensionResource(id = com.example.juricass.R.dimen.border_width), color = colorResource(id = com.example.juricass.R.color.error))
        ) {
            val img =  painterResource(id = com.example.juricass.R.drawable.baseline_error_24)
            Icon(
                painter = img,
                contentDescription = null,
                tint = colorResource(id = com.example.juricass.R.color.error),
                modifier = Modifier
                    .padding(start = dimensionResource(id = com.example.juricass.R.dimen.app_small))
            )
            Text(
                text = error.uppercase(),
                color = colorResource(id = if(reverse) com.example.juricass.R.color.white else com.example.juricass.R.color.error),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = com.example.juricass.R.dimen.app_small),
                        bottom = dimensionResource(id = com.example.juricass.R.dimen.app_small),
                        end = dimensionResource(id = com.example.juricass.R.dimen.app_small),
                        start = dimensionResource(com.example.juricass.R.dimen.border_width)
                    )
            )
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