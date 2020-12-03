package com.oussama.stackoverflow_app_example.app.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oussama.entities.User
import com.oussama.stackoverflow_app_example.R
import com.oussama.stackoverflow_app_example.app.features.core.loadUrl

class UsersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User?) {
        user?.let {
            itemView.findViewById<TextView>(R.id.name).text = user.displayName
            itemView.findViewById<TextView>(R.id.reputation).text = user.reputation.toString()
            user.profileImage.let { itemView.findViewById<ImageView>(R.id.userAvatar).loadUrl(it) }
        }
    }

    companion object {
        fun create(parent: ViewGroup): UsersHolder {
            return UsersHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
            )
        }
    }
}