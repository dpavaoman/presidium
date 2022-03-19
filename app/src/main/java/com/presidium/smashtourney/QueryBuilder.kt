package com.presidium.smashtourney

import com.apollographql.apollo3.api.composeJsonRequest
import com.apollographql.apollo3.api.json.buildJsonString
import com.apollographql.apollo3.api.json.jsonReader
import com.apollographql.apollo3.api.parseJsonResponse
import com.presidium.smashtourney.dao.EventStandingsQuery
import com.presidium.smashtourney.dao.TournamentsByVideogameQuery
import com.presidium.smashtourney.dao.searchResults.ResultType
import com.presidium.smashtourney.dao.searchResults.SearchResult

import okio.Buffer


class QueryBuilder {

    fun parseEventStandingsResponse(responseString: String): Array<SearchResult> {
        val jsonReader = Buffer().writeUtf8(responseString).jsonReader()
        val response = EventStandingsQuery().parseJsonResponse(jsonReader)
        val data: EventStandingsQuery.Data? = response.data
        val resultList: ArrayList<SearchResult> = ArrayList()
        data?.event?.standings?.nodes?.forEach {
            val result: SearchResult = SearchResult()
            result.id = it.entrant?.id
            result.resultType = ResultType.STANDING
            result.name = it.entrant.name
            resultList.add(result)
        }
        return resultList.toTypedArray()
    }

    fun parseTournamentSearchResponse(responseString: String): Array<SearchResult> {
        val jsonReader = Buffer().writeUtf8(responseString).jsonReader()
        val response = TournamentsByVideogameQuery().parseJsonResponse(jsonReader)
        val data: TournamentsByVideogameQuery.Data? = response.data
        val resultList: ArrayList<SearchResult> = ArrayList()
        data?.tournaments?.nodes?.forEach {
            val result: SearchResult = SearchResult()
            result.id = it.id
            result.resultType = ResultType.TOURNAMENT
            result.name = it.name
            resultList.add(result)
        }
        return resultList.toTypedArray()
    }
}
