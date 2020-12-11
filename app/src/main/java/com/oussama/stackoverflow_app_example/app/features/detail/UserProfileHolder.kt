package com.oussama.stackoverflow_app_example.app.features.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oussama.entities.User
import com.oussama.stackoverflow_app_example.R
import com.oussama.stackoverflow_app_example.app.features.core.loadUrl

class UserProfileHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        with(itemView) {
            findViewById<ImageView>(R.id.userAvatar).loadUrl(user.profileImage.toString())
            findViewById<TextView>(R.id.userName).text = user.displayName
            findViewById<TextView>(R.id.userPoints).text = user.reputation.toString()
        }
    }

    companion object {
        fun create(parent: ViewGroup): UserProfileHolder {
            return UserProfileHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_profile, parent, false)
            )
        }
    }
}