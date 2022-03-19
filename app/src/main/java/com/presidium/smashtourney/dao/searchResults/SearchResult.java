package com.presidium.smashtourney.dao.searchResults;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchResult implements Parcelable {

	private String id;
	private ResultType resultType;
	private String name;


	public SearchResult() {

	}

	public SearchResult(String id, ResultType resultType, String name) {
		this.id = id;
		this.resultType = resultType;
		this.name = name;
	}

	private SearchResult(Parcel in) {
		this.id = in.readString();
		this.resultType = (ResultType) in.readValue(ResultType.class.getClassLoader());
		this.name = in.readString();
	}

	public static final Parcelable.Creator<SearchResult> CREATOR
			= new Parcelable.Creator<SearchResult>() {
		public SearchResult createFromParcel(Parcel in) {
			return new SearchResult(in);
		}

		public SearchResult[] newArray(int size) {
			return new SearchResult[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(id);
		parcel.writeValue(resultType);
		parcel.writeString(name);
	}


	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
