package com.ql.giantbomb.base.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OriginalGameRating(
    var api_detail_url: String? = null,
    var id: Int = 0,
    var name: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(api_detail_url)
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OriginalGameRating> {
        override fun createFromParcel(parcel: Parcel): OriginalGameRating {
            return OriginalGameRating(parcel)
        }

        override fun newArray(size: Int): Array<OriginalGameRating?> {
            return arrayOfNulls(size)
        }
    }
}