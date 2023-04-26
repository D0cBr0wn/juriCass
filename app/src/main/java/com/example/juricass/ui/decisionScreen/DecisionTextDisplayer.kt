package com.example.juricass.ui.decisionScreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.juricass.R
import com.example.juricass.data.model.ZoneSegment

import androidx.compose.ui.res.painterResource

@Composable
fun DecisionTextDisplayer(zones: Map<String, List<ZoneSegment>>?, text: String) {
    if(zones !== null) {
        val numZones = zones?.size ?: 0
        zones?.keys?.forEachIndexed  { index, zoneName ->
            val zoneSegments = zones[zoneName]
            zoneSegments?.forEach { zoneSegment ->
                // Do something with zoneSegment
                Log.e("length", text.length.toString())
                val start = zoneSegment.start
                val end = if (index == numZones - 1) text.length else zoneSegment.end
                ZoneDisplayer(title = zoneName, text = text.substring(start, end))
            }
        }
    } else {

                Text(text, Modifier.padding(16.dp))


    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ZoneDisplayer(title: String, text: String) {
    var collapsed by remember { mutableStateOf(false) }
    var painter = if(collapsed) painterResource(id = R.drawable.baseline_expand_less_24) else painterResource(id = R.drawable.baseline_expand_more_24)
    FlowRow(horizontalArrangement = Arrangement.SpaceBetween, modifier= Modifier.padding(16.dp)) {
        Text(text = title)
        Button(onClick = { collapsed = !collapsed }, shape = CircleShape) {
            Icon(painter = painter, contentDescription = stringResource(R.string.CollapseYesNo))
        }
    }
    if(!collapsed)
        Text(text, Modifier.padding(16.dp))
}