package com.ql.giantbomb.base.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageTag(
    var api_detail_url: String? = null
    , var name: String? = null
    , var total: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(api_detail_url)
        parcel.writeString(name)
        parcel.writeInt(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageTag> {
        override fun createFromParcel(parcel: Parcel): ImageTag {
            return ImageTag(parcel)
        }

        override fun newArray(size: Int): Array<ImageTag?> {
            return arrayOfNulls(size)
        }
    }
}
