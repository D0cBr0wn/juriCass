package com.example.juricass.ui.common

import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun SearchForm() {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var startDate = remember { mutableStateOf(LocalDate.now()) }
    Column (modifier = Modifier.padding(16.dp)) {
        TextField(value = searchQuery, onValueChange = { newValue ->
            searchQuery = newValue
        })
        DatePicker(

        )
        Text(text=searchQuery)
    }

}