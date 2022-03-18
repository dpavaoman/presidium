package com.presidium.smashtourney.domain;

import com.presidium.smashtourney.QueryBuilder;
import com.presidium.smashtourney.Util.ProjectConstants;
import com.presidium.smashtourney.dao.queries.EventStandingsQueryString;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.presidium.smashtourney.dao.EventStandingsQuery;


public class QueryRetrieval implements Callable<String> {

	public QueryRetrieval(Map<String, String> variables, String query, String queryType) {
		this.variables = variables;
		this.query = query;
		this.queryType = queryType;
	}

	private final OkHttpClient httpClient = new OkHttpClient();
	private final QueryBuilder queryBuilder = new QueryBuilder();
	final Map<String, String> variables;
	final String query;
	final String queryType;

	@Override
	public String call() {

		EventStandingsQuery.Data queryData = null;
		FormBody.Builder bodyBuilder = new FormBody.Builder().add("query", query);
		for (Map.Entry<String, String> variable : variables.entrySet()) {
			bodyBuilder.add("variables", String.format("{ \"%s\" : \"%s\" }", variable.getKey(), variable.getValue()));
		}
		FormBody body = bodyBuilder.build();

		Request request = new Request.Builder()
				.url(ProjectConstants.API_URL)
				.addHeader("Authorization", ProjectConstants.API_TOKEN)
				.post(body)
				.build();
		Response response = null;

		try {
			response = httpClient.newCall(request).execute();
			if(queryType.equals("tournament")){
				return queryBuilder.parseTournamentSearchResponse(response.body().string()).toString();
			}
			else {
				return queryBuilder.parseEventStandingsResponse(response.body().string()).toString();
			}
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}
}
