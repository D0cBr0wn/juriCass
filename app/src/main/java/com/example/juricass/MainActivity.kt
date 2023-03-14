package com.example.juricass

import androidx.compose.foundation.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.juricass.ui.theme.JuriCassTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    private val mainViewModel = MainActivityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            JuriCassTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android", mainViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, viewModel: MainActivityViewModel) {
    val state by viewModel.mainActivityState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                start = 4.dp,
                end = 4.dp,
                bottom = 16.dp
            )
    ) {
    Text(text = "Hello $name!")
    Button(onClick = { viewModel.getHealthCheck() }) {
        Text(text = "Check health !!")
    }
        Text(text = state.healthCheck)
    }
    if(state.isLoading) Loader()

}

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = stringResource(R.string.loading))
//        Image(
//            modifier = Modifier.size(200.dp),
//            painter = painterResource(R.drawable.loading_img),
//            contentDescription = stringResource(R.string.loading)
//        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val mainViewModel = MainActivityViewModel()
    JuriCassTheme {
        Greeting("Android", mainViewModel)
    }
}