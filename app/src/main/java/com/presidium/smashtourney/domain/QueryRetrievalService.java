package com.presidium.smashtourney.domain;

import com.apollographql.apollo3.api.Query;
import com.presidium.smashtourney.dao.queries.EventsByTournamentsString;
import com.presidium.smashtourney.dao.searchResults.SearchResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class QueryRetrievalService {

	public <T extends Query.Data> SearchResult[] getSearchResults(Map<String, String> variables, Query<T> query ){
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Future<SearchResult[]> future = executorService.submit(new QueryRetrievalCallable(variables, query));
		executorService.shutdown();
		//Shutting down the executor service because we don't need it any more
		try {
			if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
				System.out.println("timeout");
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			executorService.shutdownNow();
		}
		SearchResult[] resultList = null;
		try {
			resultList = future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
