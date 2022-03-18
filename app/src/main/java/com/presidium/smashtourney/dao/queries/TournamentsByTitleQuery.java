package com.presidium.smashtourney.dao.queries;

public class TournamentsByTitleQuery {
	public static String query = "query TournamentsByVideogame($videogameid: ID!) {\n" +
			"    tournaments(query: {\n" +
			"      perPage: 3\n" +
			"      page: 1\n" +
			"      sortBy: \"startAt asc\"\n" +
			"      filter: {\n" +
			"        past: false\n" +
			"        videogameIds: [$videogameid]\n" +
			"      }\n" +
			"  \n" +
			"    }\n" +
			"    ) {\n" +
			"      nodes {\n" +
			"        id\n" +
			"        name\n" +
			"        slug\n" +
			"      }\n" +
			"    }}";

}
