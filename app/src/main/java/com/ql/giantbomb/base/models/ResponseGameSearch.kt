package com.ql.giantbomb.base.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGameSearch(
    var error: String? = null,
    var limit: Int = 0,
    var offset: Int = 0,
    var number_of_page_results: Int = 0,
    var number_of_total_results: Int = 0,
    var status_code: Int = 0,
    var results: List<Game>? = null,
    var version: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(Game.CREATOR),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(error)
        parcel.writeInt(limit)
        parcel.writeInt(offset)
        parcel.writeInt(number_of_page_results)
        parcel.writeInt(number_of_total_results)
        parcel.writeInt(status_code)
        parcel.writeTypedList(results)
        parcel.writeString(version)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseGameSearch> {
        override fun createFromParcel(parcel: Parcel): ResponseGameSearch {
            return ResponseGameSearch(parcel)
        }

        override fun newArray(size: Int): Array<ResponseGameSearch?> {
            return arrayOfNulls(size)
        }
    }
}

