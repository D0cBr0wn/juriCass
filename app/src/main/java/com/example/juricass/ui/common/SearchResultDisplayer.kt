package com.example.juricass.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.juricass.R
import com.example.juricass.data.model.FileLink
import com.example.juricass.data.model.SearchResult


@Composable
fun SearchResultDisplayer(result: SearchResult) {
    Column() {
        Row(modifier= Modifier.padding(8.dp)) {
            HeaderText(text = result.decisionDate)
            DashSeparator()
            HeaderText(text = result.jurisdiction)
            DashSeparator()
            HeaderText(text = stringResource(id = R.string.pourvoi_number) + result.number)
        }
        Row(modifier= Modifier.padding(8.dp)) {
            for((index, publi) in result.publication.withIndex()) {
                Text(text = publi)
                if (index != result.publication.lastIndex) {
                    DashSeparator()
                }
            }
        }
        Row(modifier= Modifier.padding(8.dp)) {
            Text(text = result.chamber)
            if(!result.formation.isNullOrEmpty()) {
                DashSeparator()
                Text(text = result.formation)
            }
        }
        Row(modifier= Modifier.padding(8.dp)) {
            Text(text = result.solution)
        }
        Row(modifier= Modifier.padding(8.dp)) {
            ThemesDisplayer(result.themes)
        }
    }

}

@Composable
fun DashSeparator() {
    Text(text = "-", modifier = Modifier.padding(horizontal = 5.dp))
}

@Composable
fun HeaderText(text: String = "") {
    Text(text = text, fontSize = 15.sp, fontWeight = FontWeight.Bold, modifier = Modifier)
}

@Composable
fun ThemesDisplayer(themes: List<String> = emptyList()) {
    if(themes.isNotEmpty()) {
        for(theme in themes) {

            Text(text= theme, modifier = Modifier.padding(horizontal = 5.dp))
        }
    }
}

@Preview("Light Theme", showBackground = true, showSystemUi = true)
@Composable
fun SearchResultDisplayerLightPreview() {
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
    SearchResultDisplayer(result)
}
//
//@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
//@Composable
//fun SearchResultDisplayerDarkPreview() {
//    val result = SearchResult(
//        score = 0.9837407640414639,
//        highlights = mapOf(
//            "text" to listOf(
//                "â€¦... et de la nue-<em>propriÃ©tÃ©</em> au groupement foncier agricole de GrisiÃ¨res (le GFA) ; que, par acte dress",
//                "â€¦... de l'usufruit des parcelles leur appartenant respectivement, et au GFA de la nue-<em>propriÃ©tÃ©</em> de ces",
//                "et ne peut jouer, sauf fraude, en cas de dÃ©membrement du droit de <em>propriÃ©tÃ©</em> ; que la fraude ne peut",
//                "mÃªme si l'usufruit et la nue-<em>propriÃ©tÃ©</em> Ã©taient cÃ©dÃ©s Ã  deux personnes distinctes, la cour d'appel",
//                "GaÃ«tan â€¦... et de la nue-<em>propriÃ©tÃ©</em> au GFA de GrisiÃ¨res dont M"
//            )
//        ),
//        id = "5fca8ed3ce4c46819cdeab10",
//        jurisdiction = "Cour de cassation",
//        chamber = "TroisiÃ¨me chambre civile",
//        number = "16-25.829",
//        numbers = listOf("16-25.829"),
//        ecli = "ECLI:FR:CCASS:2018:C300505",
//        formation = "Formation de section",
//        publication = listOf("PubliÃ© au Bulletin"),
//        decisionDate = "2018-05-31",
//        type = "Autre",
//        solution = "Cassation",
//        solutionAlt = null,
//        summary = "L'aliÃ©nation simultanÃ©e, Ã  titre onÃ©reux, de l'usufruit et de la nue-propriÃ©tÃ© d'u",
//        bulletin = null,
//        files = emptyList(),
//        themes = listOf(
//            "societe d'amenagement foncier et d'etablissement rural",
//            "prÃ©emption",
//            "domaine d'application",
//            "vente de la pleine propriÃ©tÃ© du bien",
//            "conditions",
//            "dÃ©termination"
//        )
//    )
//    SearchResultDisplayer(result)
//}