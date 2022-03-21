package com.presidium.smashtourney.dao.queries;

public class EventsByTournamentsString {
	public static String query = "query EventsByTournament($tournamentId: ID!) {\n" +
			"   tournament(id: $tournamentId) {\n" +
			"     id\n" +
			"     events {\n" +
			"       name\n" +
			"       videogame {\n" +
			"         id\n" +
			"       }\n" +
			"       id\n" +
			"     }\n" +
			"   }\n" +
			" }";
}
