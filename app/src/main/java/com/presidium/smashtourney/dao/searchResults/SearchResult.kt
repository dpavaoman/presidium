package com.presidium.smashtourney.dao.searchResults

import android.os.Parcelable
import com.presidium.smashtourney.dao.searchResults.ResultType
import android.os.Parcel
import android.os.Parcelable.Creator
import kotlinx.parcelize.Parcelize

@Parcelize
class SearchResult(val id: String?, val resultType: ResultType?, val name: String?) : Parcelable