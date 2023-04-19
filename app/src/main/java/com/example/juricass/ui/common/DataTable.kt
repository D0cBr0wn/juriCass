package com.example.juricass.ui.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class TableMap(title: String, propName: String, weight: Float = 1f) {
    val title: String = title
    val propName: String = propName
    val weight: Float = weight
}

@Composable
fun DataTable(map: List<TableMap>, items: List<Any>, isLoading: Boolean = false) {
    LazyColumn {
        item {
            HeaderRow(map)
            SkeletonLoader(isLoading)
        }
        itemsIndexed(items) { index, item ->
            TableRow(tableMaps = map, item= item, index = index)
        }
    }
}

@Composable
fun RowBase(
    tableMaps: List<TableMap>,
    item: Any,
    content: @Composable (TableMap, Any, Modifier) -> Unit,
    weight: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.background,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(backgroundColor)
    ) {
        tableMaps.forEach { tableMap ->
            content(tableMap, item, Modifier.weight(weight).align(Alignment.CenterVertically))
        }
    }
}

@Composable
fun HeaderRow(tableMaps: List<TableMap>) {
    RowBase(
        tableMaps = tableMaps,
        item= "",
        backgroundColor = MaterialTheme.colors.onBackground,
        content = { tableMap, item, contentModifier ->
            Text(
                text = tableMap.title,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White,
                modifier = contentModifier.padding(vertical = 16.dp, horizontal = 4.dp)
            )
        }
    )
}

@Composable
fun TableRow(tableMaps: List<TableMap>, item: Any, index: Int) {
    RowBase(
        tableMaps = tableMaps,
        item = item,
        backgroundColor = if (index % 2 == 0) {
            MaterialTheme.colors.surface
        } else {
            MaterialTheme.colors.background
        },
        content = { tableMap, item, contentModifier ->
            runCatching {
                val property = item::class.java.getDeclaredField(tableMap.propName)
                property.isAccessible = true
                val value = property.get(item)
                Text(
                    text = value?.toString() ?: "",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = contentModifier.padding(4.dp)
                )
            }.onFailure {
                Log.e("DataTableError", "Error: ${it.message}")
            }


        }
    )
}