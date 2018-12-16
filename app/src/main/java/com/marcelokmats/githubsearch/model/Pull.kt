package com.marcelokmats.githubsearch.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Pull(
    @SerializedName("id") private val mId: Int,
    @SerializedName("number") private val mNumber: Long,
    @SerializedName("title") private val mTitle: String
) : Parcelable {

    val number
        get() = mNumber

    val title
        get() = mTitle

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mId)
        parcel.writeLong(mNumber)
        parcel.writeString(mTitle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pull> {
        override fun createFromParcel(parcel: Parcel): Pull {
            return Pull(parcel)
        }

        override fun newArray(size: Int): Array<Pull?> {
            return arrayOfNulls(size)
        }
    }

}