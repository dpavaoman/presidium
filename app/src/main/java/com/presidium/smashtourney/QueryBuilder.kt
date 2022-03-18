package com.presidium.smashtourney

import com.apollographql.apollo3.api.composeJsonRequest
import com.apollographql.apollo3.api.json.buildJsonString
import com.apollographql.apollo3.api.json.jsonReader
import com.apollographql.apollo3.api.parseJsonResponse
import com.presidium.smashtourney.dao.EventStandingsQuery
import com.presidium.smashtourney.dao.TournamentsByVideogameQuery

import okio.Buffer


class QueryBuilder {

    fun parseEventStandingsResponse(responseString: String): EventStandingsQuery.Data {
        val jsonReader = Buffer().writeUtf8(responseString).jsonReader()
        val response = EventStandingsQuery().parseJsonResponse(jsonReader)
        return response.data!!
    }

    fun parseTournamentSearchResponse(responseString: String): TournamentsByVideogameQuery.Data {
        val jsonReader = Buffer().writeUtf8(responseString).jsonReader()
        val response = TournamentsByVideogameQuery().parseJsonResponse(jsonReader)
        return response.data!!
    }
}
