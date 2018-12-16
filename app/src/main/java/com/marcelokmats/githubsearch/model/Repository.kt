package com.marcelokmats.githubsearch.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id") private val mId: Int,
    @SerializedName("name") private val mName: String,
    @SerializedName("description") private val mDescription: String,
    @SerializedName("owner") private val mUser: User
) : Parcelable {

    val id
        get() = mId

    val name
        get() = mName

    val user
        get() = mUser

    val description
        get() = mDescription

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(User::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mId)
        parcel.writeString(mName)
        parcel.writeString(mDescription)
        parcel.writeParcelable(mUser, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repository> {
        override fun createFromParcel(parcel: Parcel): Repository {
            return Repository(parcel)
        }

        override fun newArray(size: Int): Array<Repository?> {
            return arrayOfNulls(size)
        }
    }


}