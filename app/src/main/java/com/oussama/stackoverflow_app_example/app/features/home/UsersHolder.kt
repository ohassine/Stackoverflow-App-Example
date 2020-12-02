package com.oussama.stackoverflow_app_example.app.features.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oussama.stackoverflow_app_example.R

class UsersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.name)
    val reputation: TextView = itemView.findViewById(R.id.reputation)
    val userAvatar: ImageView = itemView.findViewById(R.id.userAvatar)
}