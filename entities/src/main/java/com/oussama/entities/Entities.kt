package com.oussama.entities

import android.os.Parcel
import com.google.gson.annotations.SerializedName
import android.os.Parcelable

data class User(
    @SerializedName("user_id") val userId: Long,
    @SerializedName("display_name") val displayName: String?,
    @SerializedName("reputation") val reputation: Long,
    @SerializedName("profile_image") val profileImage: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(userId)
        parcel.writeString(displayName)
        parcel.writeLong(reputation)
        parcel.writeString(profileImage)
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

data class Question(
    @SerializedName("view_count") var viewCount: Long,
    @SerializedName("score") var score: Long,
    @SerializedName("title") var title: String,
    @SerializedName("link") var link: String,
    @SerializedName("question_id") var questionId: Long,
    @SerializedName("owner") var owner: Owner
) {
    constructor() : this(0, 0, "", "", -1, Owner())
}

data class Answer(
    @SerializedName("answer_id") var answerId: Long,
    @SerializedName("question_id") var questionId: Long,
    @SerializedName("score") var score: Long,
    @SerializedName("is_accepted") var accepted: Boolean,
    @SerializedName("owner") var owner: Owner
) {
    constructor() : this(-1, -1, 0, false, Owner())
}

data class Owner(@SerializedName("user_id") var userId: Long) {
    constructor() : this(-1)
}