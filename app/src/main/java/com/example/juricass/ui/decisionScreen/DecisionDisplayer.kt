package com.example.juricass.ui.decisionScreen

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
import com.example.juricass.R
import com.example.juricass.data.model.Decision
import com.example.juricass.data.model.Judgement

import com.example.juricass.ui.common.CircleLoader
import com.example.juricass.ui.common.DashSeparator

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DecisionDisplayer(decision: Decision?, modifier: Modifier = Modifier.testTag("decisionDisplayer")) {
    val rowModifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    if(decision == null) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement  = Arrangement.Center) {
            Text(text = stringResource(R.string.noDecisionFound), modifier = Modifier.testTag("nothingFound"))
        }

    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
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
//
//@Preview("Light Theme", showBackground = true, showSystemUi = true)
//@Composable
//fun DecisionDisplayerPreview() {
//    val decision = Decision(
//        "5fd91ab6eb0eedb6c7f7b760",
//        "jurinet",
//        {
//            "introduction": [
//            {
//                "start": 0,
//                "end": 1495
//            }
//            ],
//            "expose": [
//            {
//                "start": 1520,
//                "end": 2312
//            }
//            ],
//            "moyens": [
//            {
//                "start": 1496,
//                "end": 1520
//            },
//            {
//                "start": 2312,
//                "end": 6790
//            }
//            ],
//            "motivations": [
//            {
//                "start": 6790,
//                "end": 8935
//            }
//            ],
//            "dispositif": [
//            {
//                "start": 8938,
//                "end": 9394
//            }
//            ],
//            "annexes": [
//            {
//                "start": 9394,
//                "end": 24963
//            }
//            ]
//        },
//        "PremiÃ¨re chambre civile",
//        "2016-11-03",
//        "ECLI:FR:CCASS:2016:C101219",
//        "Cour de cassation",
//        "15-24.189",
//        listOf("15-24.189"),
//        listOf("PubliÃ© au Bulletin"),
//        "Rejet",
//        "Autre",
//        "Formation restreinte hors RNSM/NA",
//        "2018-07-11",
//        "Une cour d'appel dÃ©cide exactement que l'article L. 411-4 du code de la propriÃ©tÃ© intellectuelle...",
//        listOf(
//            "propriete industrielle",
//            "organisation administrative et professionnelle",
//            "institut national de la propriÃ©tÃ© industrielle",
//            "recours contre les dÃ©cisions du directeur gÃ©nÃ©ral",
//            "compÃ©tence de la cour d'appel en premier et dernier ressort",
//            "dÃ©rogation au double degrÃ© de juridiction",
//            "violation d'un principe gÃ©nÃ©ral du droit ayant valeur constitutionnelle (non)"
//        ),
//        "bulletin",
//        "2014-08-11",
//        emptyList(),
//        "rejet",
//        Judgement(
//            "60372db92e21be08d49c1472",
//            "2015-05-26",
//            "cour d'appel de Paris\nPÃ´le 5 - Chambre 1",
//            "14/00839",
//            "60372db92e21be08d49c1472"
//        )
//    )
//    DecisionDisplayer(decision)
//}