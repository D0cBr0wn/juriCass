package com.example.juricass.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.juricass.R
import com.example.juricass.data.model.FileLink
import com.example.juricass.data.model.SearchResult
import com.example.juricass.ui.JuriCassRoutes
import com.example.juricass.ui.theme.JuriCassTheme


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchResultDisplayer(result: SearchResult, navController: NavController) {
    val rowModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    Surface(elevation =1.dp, modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp)) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        FlowRow(horizontalArrangement = Arrangement.Start, modifier= rowModifier) {
            HeaderText(text = result.formattedDecisionDate)
            DashSeparator()
            HeaderText(text = result.jurisdiction)
            DashSeparator()
            HeaderText(text = stringResource(id = R.string.pourvoi_number) + result.number)
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.onSurface)
            )
        }
        FlowRow(horizontalArrangement = Arrangement.Start, modifier= rowModifier) {
            for((index, publi) in result.publication.withIndex()) {
                Text(text = publi, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primaryVariant)
                if (index != result.publication.lastIndex) {
                    DashSeparator()
                }
            }
        }
        FlowRow(horizontalArrangement = Arrangement.Start, modifier= rowModifier) {
            Text(text = result.chamber, fontWeight = FontWeight.Bold)
            if(!result.formation.isNullOrEmpty()) {
                DashSeparator()
                Text(text = result.formation, fontWeight = FontWeight.Bold)
            }
        }
        Row(modifier= rowModifier) {
            Text(text = result.solution, fontWeight = FontWeight.Bold)
        }
        if(result.summary?.isNotEmpty() == true) {
            Row(modifier= rowModifier) {
                Text(text = result.summary)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(JuriCassRoutes.DECISION.name + "/${result.id}") },
            modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.read))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ThemesDisplayer(result.themes, isSystemInDarkTheme())
        }
    }
}



@Composable
fun HeaderText(text: String = "") {
    Text(text = text, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ThemesDisplayer(themes: List<String>, outlined: Boolean = true) {
    if(themes.isNotEmpty()) {
        FlowRow(horizontalArrangement = Arrangement.Start ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colors.secondary)
            )
            Spacer(modifier = Modifier.height(8.dp))
            for(theme in themes) {
                Pill(theme, outlined)
            }
        }

    }
}

@Preview("Light Theme", showBackground = true, showSystemUi = true)
@Composable
fun SearchResultDisplayerLightPreview() {
    val navController = rememberNavController()
    val result = SearchResult(
        score = 0.9837407640414639,
        highlights = mapOf(
            "text" to listOf(
                "â€¦... et de la nue-<em>propriÃ©tÃ©</em> au groupement foncier agricole de GrisiÃ¨res (le GFA) ; que, par acte dress",
                "â€¦... de l'usufruit des parcelles leur appartenant respectivement, et au GFA de la nue-<em>propriÃ©tÃ©</em> de ces",
                "et ne peut jouer, sauf fraude, en cas de dÃ©membrement du droit de <em>propriÃ©tÃ©</em> ; que la fraude ne peut",
                "mÃªme si l'usufruit et la nue-<em>propriÃ©tÃ©</em> Ã©taient cÃ©dÃ©s Ã  deux personnes distinctes, la cour d'appel",
                "GaÃ«tan â€¦... et de la nue-<em>propriÃ©tÃ©</em> au GFA de GrisiÃ¨res dont M"
            )
        ),
        id = "5fca8ed3ce4c46819cdeab10",
        jurisdiction = "Cour de cassation",
        chamber = "TroisiÃ¨me chambre civile",
        number = "16-25.829",
        numbers = listOf("16-25.829"),
        ecli = "ECLI:FR:CCASS:2018:C300505",
        formation = "Formation de section",
        publication = listOf("PubliÃ© au Bulletin"),
        decisionDate = "2018-05-31",
        type = "Autre",
        solution = "Cassation",
        solutionAlt = null,
        summary = "L'aliÃ©nation simultanÃ©e, Ã  titre onÃ©reux, de l'usufruit et de la nue-propriÃ©tÃ© d'u",
        bulletin = null,
        files = emptyList(),
        themes = listOf(
            "societe d'amenagement foncier et d'etablissement rural",
            "prÃ©emption",
            "domaine d'application",
            "vente de la pleine propriÃ©tÃ© du bien",
            "conditions",
            "dÃ©termination"
        )
    )
    SearchResultDisplayer(result, navController)
}
