package com.marcelokmats.githubsearch.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Issue(
    @SerializedName("id") private val mId: Int,
    @SerializedName("user") private val mUser: User,
    @SerializedName("number") private val mNumber: Long,
    @SerializedName("title") private val mTitle: String,
    @SerializedName("body") private val mBody: String
) : Parcelable {

    val user
        get() = mUser

    val number
        get() = mNumber

    val title
        get() = mTitle

    val body
        get() = mBody

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(User::class.java.classLoader),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mId)
        parcel.writeParcelable(mUser, flags)
        parcel.writeLong(mNumber)
        parcel.writeString(mTitle)
        parcel.writeString(mBody)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Issue> {
        override fun createFromParcel(parcel: Parcel): Issue {
            return Issue(parcel)
        }

        override fun newArray(size: Int): Array<Issue?> {
            return arrayOfNulls(size)
        }
    }


}