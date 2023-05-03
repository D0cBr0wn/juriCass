package com.example.juricass.ui.decisionScreen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.juricass.R
import com.example.juricass.data.fixtures.DecisionFixture
import com.example.juricass.data.model.Decision
import com.example.juricass.data.model.Judgement
import com.example.juricass.data.state.DecisionState

import com.example.juricass.ui.common.CircleLoader
import com.example.juricass.ui.common.DashSeparator
import com.example.juricass.ui.theme.JuriCassTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DecisionDisplayer(decision: Decision?, modifier: Modifier = Modifier) {
    val rowModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    if(decision == null) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement  = Arrangement.Center) {
            Text(text = stringResource(R.string.noDecisionFound), modifier = Modifier.testTag("nothingFound"))
        }

    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth().testTag("solutionDisplayer")) {
            item {
                FlowRow(horizontalArrangement = Arrangement.Start, modifier = rowModifier) {
                    val typo = MaterialTheme.typography.h4
                    val modifier = Modifier.padding(end = 8.dp)
                    Text(text = decision.formattedDecisionDate, style = typo, modifier = modifier)
                    Text(text = decision.jurisdiction, style = typo, modifier = modifier)
                    Text(
                        text = stringResource(id = R.string.pourvoi_number) + decision.number,
                        style = typo,
                        modifier = modifier
                    )
                }

                FlowRow(horizontalArrangement = Arrangement.Start, modifier = rowModifier) {
                    val modifier = Modifier.padding(end = 8.dp)
                    Text(text = decision.chamber, fontWeight = FontWeight.Bold, modifier = modifier)
                    if (decision.formation != null) {
                        DashSeparator()
                        Text(
                            text = decision.formation,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier
                        )
                    }
                }

                FlowRow(horizontalArrangement = Arrangement.Start, modifier = rowModifier) {
                    for ((index, publi) in decision.publication.withIndex()) {
                        Text(
                            text = publi,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primaryVariant
                        )
                        if (index != decision.publication.lastIndex) {
                            DashSeparator()
                        }
                    }
                }

                if (decision.ecli !== null)
                    Row(modifier = rowModifier) {
                        Text(text = decision.ecli)
                    }

                FlowRow(horizontalArrangement = Arrangement.Start, modifier = rowModifier) {
                    Text(
                        text = stringResource(R.string.textOfDecision),
                        style = MaterialTheme.typography.h4
                    )
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colors.secondary)
                    )
                }

                DecisionTextDisplayer(zones = decision.zones, text = decision.text)
            }
        }
    }
}


@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun DecisionDisplayerPreviewDark() {
    val navController = rememberNavController()
    JuriCassTheme() {
        DecisionDisplayer(decision = DecisionFixture.decision())
    }
}
@Preview("Light Theme", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Composable
fun DecisionDisplayerPreview() {
    val navController = rememberNavController()
    JuriCassTheme() {
        DecisionDisplayer(decision = DecisionFixture.decision())
    }
}

