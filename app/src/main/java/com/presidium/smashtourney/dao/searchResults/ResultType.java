package com.presidium.smashtourney.dao.searchResults;

public enum ResultType {

	EVENT("EVENT"),
	TOURNAMENT("TOURNAMENT"),
	STANDING("STANDING");

	public String resultType;

	ResultType(String resultType){
		this.resultType = resultType;
	}

	public String getResultType() {
		return resultType;
	}


}
