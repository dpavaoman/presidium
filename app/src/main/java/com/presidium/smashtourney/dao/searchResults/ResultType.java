package com.presidium.smashtourney.dao.searchResults;

public enum ResultType {

	EVENT("EVENT"),
	TOURNAMENT("TOURNAMENT"),
	TITLE("TITLE"),
	STANDING("STANDING");

	public String resultType;

	ResultType(String resultType){
		this.resultType = resultType;
	}

	public String getResultType() {
		return resultType;
	}


}
