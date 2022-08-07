package com.ql.giantbomb.base.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Platform(
    var api_detail_url: String? = null,
    var name: String? = null,
    var id: Int = 0,
    var site_detail_url: String? = null,
    var abbreviation: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(api_detail_url)
        parcel.writeString(name)
        parcel.writeInt(id)
        parcel.writeString(site_detail_url)
        parcel.writeString(abbreviation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Platform> {
        override fun createFromParcel(parcel: Parcel): Platform {
            return Platform(parcel)
        }

        override fun newArray(size: Int): Array<Platform?> {
            return arrayOfNulls(size)
        }
    }
}
