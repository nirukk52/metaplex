package com.ql.giantbomb.base.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    var icon_url: String? = null,
    var medium_url: String? = null,
    var screen_url: String? = null,
    var screen_large_url: String? = null,
    var small_url: String? = null,
    var super_url: String? = null,
    var thumb_url: String? = null,
    var tiny_url: String? = null,
    var original_url: String? = null,
    var image_tags: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(icon_url)
        writeString(medium_url)
        writeString(screen_url)
        writeString(screen_large_url)
        writeString(small_url)
        writeString(super_url)
        writeString(thumb_url)
        writeString(tiny_url)
        writeString(original_url)
        writeString(image_tags)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Image> = object : Parcelable.Creator<Image> {
            override fun createFromParcel(source: Parcel): Image = Image(source)
            override fun newArray(size: Int): Array<Image?> = arrayOfNulls(size)
        }
    }
}