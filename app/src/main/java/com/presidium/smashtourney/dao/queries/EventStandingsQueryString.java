package com.presidium.smashtourney.dao.queries;

public class EventStandingsQueryString {

public static String query = "query EventStandings {\n" +
								"  event(id: 78790) {\n" +
								"    id\n" +
								"    name\n" +
								"    standings(query: {\n" +
								"      perPage: 3,\n" +
								"      page: 1\n" +
								"    }){\n" +
								"      nodes {\n" +
								"        placement\n" +
								"        entrant {\n" +
								"          id\n" +
								"          name\n" +
								"        }\n" +
								"      }\n" +
								"    }\n" +
								"  }\n" +
								"}\n";
}
