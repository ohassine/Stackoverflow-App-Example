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
    @SerializedName("view_count") var viewCount: Long = 0,
    @SerializedName("score") var score: Long = 0,
    @SerializedName("title") var title: String = "",
    @SerializedName("link") var link: String = "",
    @SerializedName("question_id") var questionId: Long = 0,
    @SerializedName("owner") var owner: Owner? = null
)

data class Answer(
    @SerializedName("answer_id") var answerId: Long = 0,
    @SerializedName("question_id") var questionId: Long = 0 ,
    @SerializedName("score") var score: Long =0 ,
    @SerializedName("is_accepted") var accepted: Boolean = false,
    @SerializedName("owner") var owner: Owner? = null,
    var questionTitle : String = ""
)

data class Owner(@SerializedName("user_id") var userId: Long)

data class UserDetail(
    val questions: List<Question>,
    val answers: List<Answer>,
    val favorites: List<Question>
)