package com.example.juricass.data.fixtures

import com.example.juricass.data.model.Decision
import com.example.juricass.data.model.ZoneSegment

class DecisionFixture {
    companion object {
        fun decision(zones: Map<String, List<ZoneSegment>>? = mapOf(
            "introduction" to listOf(
                ZoneSegment(0, 1495)
            )
        )): Decision {
            return Decision(
                id = "decisionId",
                source = "source",
                text = "loremipsum text",
                jurisdiction = "cc",
                chamber = "chamber",
                number = "3456787654",
                numbers = listOf("567876", "567898767"),
                publication = listOf("publi-567876", "publi-567898767"),
                decisionDate = "2023-02-28",
                type = "type",
                solution = "rejet",
                summary = "summary",
                themes = listOf("theme-567876", "theme-567898767"),
                partial = false,
                zones = zones
            )
        }

        fun noZonesDecision(): Decision {
            return decision(zones = null)
        }
    }
}