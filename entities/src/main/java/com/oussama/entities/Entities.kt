package com.oussama.entities

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id") val userId : Long,
    @SerializedName("display_name") val displayName : String,
    @SerializedName("reputation") val reputation : Long,
    @SerializedName("profile_image") val profileImage : String
)