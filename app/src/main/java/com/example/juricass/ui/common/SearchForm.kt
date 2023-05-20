package com.example.juricass.ui.common

import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.juricass.R
import com.example.juricass.data.state.HomeState

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchForm(
    state: HomeState,
    onSearchQueryChanged: (String) -> Unit,
    onStartDateSet: (LocalDate) -> Unit,
    onEndDateSet: (LocalDate) -> Unit,
    onExactSet: (Boolean) -> Unit,
    resetFields: () -> Unit
) {

    var popStartDate by remember { mutableStateOf(false)}
    var popEndDate by remember { mutableStateOf(false)}
    var searchQuery by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false)}

    Column (modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        FlowRow(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = checked,
                onCheckedChange = {
                        newValue ->
                    onExactSet(newValue)
                    checked = newValue
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.secondary,
                    checkedTrackColor = MaterialTheme.colors.secondaryVariant,
                    uncheckedThumbColor = MaterialTheme.colors.onSurface,
                )
            )
            Text(text="Exact match", modifier = Modifier.padding(start = 8.dp))
        }

        TextField(value = searchQuery, onValueChange = { newValue ->
            searchQuery = newValue
            onSearchQueryChanged(searchQuery)
        },
        modifier = Modifier.fillMaxWidth())

        FlowRow(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                popStartDate = true
            }) {
                Text(text= if(state.startDate !== "") state.startDate else "start date")
            }

            Button(onClick = {
                popEndDate = true
            }) {
                Text(text= if(state.endDate !== "") state.endDate else "end date")
            }
        }
        
        Spacer(modifier = Modifier.height(30.dp))

        FlowRow(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .weight(0.85f)
                .height(70.dp)) {
                Text(text = "Search")
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
            IconButton(
                onClick = {
                    resetFields()
                    searchQuery = ""
                    checked = false
                },
                modifier = Modifier.weight(0.15f)) {
                Icon(Icons.Filled.Close , contentDescription = "reset fields", tint = MaterialTheme.colors.secondary)
            }


        }

        if(popStartDate|| popEndDate) {
            JuriDatePicker(
                onDateSelected = {
                    newDate -> if(popStartDate ) onStartDateSet(newDate) else onEndDateSet(newDate)
                    popStartDate = false
                    popEndDate = false
                },
                onDismissRequest = {
                    popStartDate = false
                    popEndDate = false
                }
            )
        }
        
    }


}