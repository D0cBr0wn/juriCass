package com.example.juricass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.juricass.ui.theme.JuriCassTheme
import androidx.compose.runtime.getValue
import com.example.juricass.ui.JuriCassApp
import com.example.juricass.ui.common.LoaderAndErrorDisplayer
import com.example.juricass.ui.common.TopBar

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
                    Scaffold(
                        topBar = { TopBar() },
                        modifier = Modifier,
                        content = { padding -> Column(modifier = Modifier.padding(padding)) {
                            Box(modifier = Modifier.padding(2.dp)) {
                                JuriCassApp()
                            }
                        } }
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val mainViewModel = MainActivityViewModel()
    JuriCassTheme {
        JuriCassApp()
    }
}