package com.oussama.stackoverflow_app_example.app.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oussama.entities.User
import com.oussama.stackoverflow_app_example.R
import com.oussama.stackoverflow_app_example.app.features.core.loadUrl

class UsersAdapter : RecyclerView.Adapter<UsersHolder>() {

    private var users: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        return UsersHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.name.text = users[position].displayName
        holder.reputation.text = users[position].reputation.toString()
        holder.userAvatar.loadUrl(users[position].profileImage)
    }

    override fun getItemCount(): Int = users.size

    fun setList(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }
}