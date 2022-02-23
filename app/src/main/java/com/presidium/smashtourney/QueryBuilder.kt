package com.presidium.smashtourney

import com.apollographql.apollo3.api.composeJsonRequest
import com.apollographql.apollo3.api.json.buildJsonString
import com.apollographql.apollo3.api.json.jsonReader
import com.apollographql.apollo3.api.parseJsonResponse
import com.presidium.smashtourney.dao.EventStandingsQuery
import okio.Buffer


class QueryBuilder {
    fun getEventQueryString(): String {
        val body = buildJsonString {
            EventStandingsQuery().composeJsonRequest(this)
        }
        return body
    }

    fun parseResponse(responseString: String): EventStandingsQuery.Data {
        val jsonReader = Buffer().writeUtf8(responseString).jsonReader()
        val response = EventStandingsQuery().parseJsonResponse(jsonReader)
        return response.data!!
    }
}
