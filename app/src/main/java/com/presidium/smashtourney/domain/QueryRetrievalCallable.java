package com.presidium.smashtourney.domain;

import com.apollographql.apollo3.api.Query;
import com.presidium.smashtourney.Util.ProjectConstants;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.presidium.smashtourney.dao.searchResults.SearchResult;


public class QueryRetrievalCallable implements Callable<SearchResult[]> {

	public <T extends Query.Data> QueryRetrievalCallable(Map<String, String> variables, Query<T> query) {
		this.variables = variables;
		this.query = query;
	}

	private final OkHttpClient httpClient = new OkHttpClient();
	private final QueryBuilder queryBuilder = new QueryBuilder();
	final Map<String, String> variables;
	final Query<?> query;

	@Override
	public SearchResult[] call() {

//		FormBody.Builder bodyBuilder = new FormBody.Builder().add("query", query.toString());
//		for (Map.Entry<String, String> variable : variables.entrySet()) {
//			bodyBuilder.add("variables", String.format("{ \"%s\" : \"%s\" }", variable.getKey(), variable.getValue()));
//		}
//		FormBody body = bodyBuilder.build();
//
//		Request request = new Request.Builder()
//				.url(ProjectConstants.API_URL)
//				.addHeader("Authorization", ProjectConstants.API_TOKEN)
//				.post(body)
//				.build();
//		Response response = null;
//		int count = 0;
//		while (count < 3){
//			try {
//				response = httpClient.newCall(request).execute();
//				if (query instanceof TournamentsByVideogameQuery) {
//					return queryBuilder.parseTournamentSearchResponse(response.body().string());
//				}
//				else if (query instanceof EventsByTournamentQuery){
//					return queryBuilder.parseEventByTournamentResponse(response.body().string());
//				}
//				else {
//					return queryBuilder.parseStandingsResponse(response.body().string());
//				}
//			} catch (IOException | NullPointerException e) {
//				e.printStackTrace();
//				count++;
//			}
//		}
		return null;
	}
}
