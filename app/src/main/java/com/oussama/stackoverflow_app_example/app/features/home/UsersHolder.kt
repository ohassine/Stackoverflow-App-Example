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

    fun bind(user: User?, clickListener: (user: User?) -> Unit) = with(itemView) {

        user?.let {
            findViewById<TextView>(R.id.name).text = user.displayName
            findViewById<TextView>(R.id.reputation).text = user.reputation.toString()
            user.profileImage.let {
                if (it != null) {
                    findViewById<ImageView>(R.id.userAvatar).loadUrl(it)
                }
            }
        }


        setOnClickListener {
            clickListener(user)
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