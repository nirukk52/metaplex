package com.ql.giantbomb.base.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Game(
    var aliases: String? = null,
    var api_detail_url: String? = null,
    var date_added: String? = null,
    var date_last_updated: String? = null,
    var deck: String? = null,
    var description: String? = null,
    var expected_release_day: String? = null,
    var expected_release_month: String? = null,
    var expected_release_quarter: String? = null,
    var expected_release_year: String? = null,
    var guid: String? = null,
    var id: Int = 0,
    var image: Image? = null,
    var image_tags: List<ImageTag>? = null,
    var name: String? = null,
    var number_of_user_reviews: Int = 0,
    var original_game_rating: List<OriginalGameRating>? = null,
    var original_release_date: String? = null,
    var platforms: List<Platform>? = null,
    var site_detail_url: String? = null
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
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readParcelable(Image::class.java.classLoader),
        ArrayList<ImageTag>().apply {
            source.readList(
                this as List<*>,
                ImageTag::class.java.classLoader
            )
        },
        source.readString(),
        source.readInt(),
        ArrayList<OriginalGameRating>().apply {
            source.readList(
                this as List<*>,
                OriginalGameRating::class.java.classLoader
            )
        },
        source.readString(),
        ArrayList<Platform>().apply {
            source.readList(
                this as List<*>,
                Platform::class.java.classLoader
            )
        },
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(aliases)
        writeString(api_detail_url)
        writeString(date_added)
        writeString(date_last_updated)
        writeString(deck)
        writeString(description)
        writeString(expected_release_day)
        writeString(expected_release_month)
        writeString(expected_release_quarter)
        writeString(expected_release_year)
        writeString(guid)
        writeInt(id)
        writeParcelable(image, 0)
        writeList(image_tags)
        writeString(name)
        writeInt(number_of_user_reviews)
        writeList(original_game_rating)
        writeString(original_release_date)
        writeList(platforms)
        writeString(site_detail_url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Game> = object : Parcelable.Creator<Game> {
            override fun createFromParcel(source: Parcel): Game = Game(source)
            override fun newArray(size: Int): Array<Game?> = arrayOfNulls(size)
        }
    }
}