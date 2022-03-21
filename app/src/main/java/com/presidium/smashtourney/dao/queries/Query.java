package com.presidium.smashtourney.dao.queries;

public enum Query {

	STANDINGS_BY_EVENT(EventStandingsQueryString.query),
	EVENTS_BY_TOURNAMENT(EventsByTournamentsString.query),
	TOURNAMENTS_BY_TITLE(TournamentsByTitleQuery.query);

	Query(String query){
		this.query = query;
	}

	public String query;



}
