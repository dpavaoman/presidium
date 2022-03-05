package com.presidium.smashtourney.domain;

import com.presidium.smashtourney.QueryBuilder;
import com.presidium.smashtourney.Util.ProjectConstants;
import com.presidium.smashtourney.dao.queries.EventStandingsQueryString;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.presidium.smashtourney.dao.EventStandingsQuery;


public class QueryRetrieval implements Callable<String> {


	private final OkHttpClient httpClient = new OkHttpClient();
	private final QueryBuilder queryBuilder = new QueryBuilder();

	@Override
	public String call() throws Exception {

		EventStandingsQuery.Data queryData = null;

		String query = EventStandingsQueryString.query;
		RequestBody body = new FormBody.Builder()
				.add("query", query)
				.build();

		Request request = new Request.Builder()
				.url(ProjectConstants.API_URL)
				.addHeader("Authorization", ProjectConstants.API_TOKEN)
				.post(body)
				.build();
		Response response = null;

		try {
			response = httpClient.newCall(request).execute();
			queryData = queryBuilder.parseResponse(response.body().string());
			System.out.println(queryData);
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
		return queryData.toString();
	}
}
