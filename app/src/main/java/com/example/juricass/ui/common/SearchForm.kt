package com.example.juricass.ui.common

import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SearchForm() {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var pickedStartDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedSTartDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM-dd-yyyy")
                .format(pickedStartDate)
        }
    }

    Column (modifier = Modifier.padding(16.dp)) {
        TextField(value = searchQuery, onValueChange = { newValue ->
            searchQuery = newValue
        })
        Button(onClick = {
            
        }) {
            Text(text="start date")
        }
        Text(text=searchQuery)
        Text(text = pickedStartDate.toString())
        //DatePicker(onDateSelected = {newDate -> pickedStartDate = newDate}) {
        //
        //}
        
    }


}