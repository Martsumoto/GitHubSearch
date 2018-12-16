package com.marcelokmats.githubsearch.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") private val mId: Int,
    @SerializedName("number") private val mNumber: Long,
    @SerializedName("login") private val mLogin: String,
    @SerializedName("avatar_url") private val mAvatarUrl: String
) : Parcelable {

    val number
        get() = mNumber

    val login
        get() = mLogin

    val avatarUrl
        get() = mAvatarUrl

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mId)
        parcel.writeLong(mNumber)
        parcel.writeString(mLogin)
        parcel.writeString(mAvatarUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}