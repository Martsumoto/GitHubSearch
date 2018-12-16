package com.marcelokmats.githubsearch.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class IssueComment(
    @SerializedName("id") private val mId: Int,
    @SerializedName("user") private val mUser: User,
    @SerializedName("body") private val mBody: String
) : Parcelable {

    val id
        get() = mId

    val user
        get() = mUser

    val body
        get() = mBody

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(User::class.java.classLoader),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(mId)
        parcel.writeParcelable(mUser, flags)
        parcel.writeString(mBody)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IssueComment> {
        override fun createFromParcel(parcel: Parcel): IssueComment {
            return IssueComment(parcel)
        }

        override fun newArray(size: Int): Array<IssueComment?> {
            return arrayOfNulls(size)
        }
    }


}