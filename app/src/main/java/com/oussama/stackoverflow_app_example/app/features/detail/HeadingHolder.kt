package com.oussama.stackoverflow_app_example.app.features.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oussama.stackoverflow_app_example.R

class HeadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(title: String) {
        with(itemView) {
            findViewById<TextView>(R.id.headingTitle).text = title
        }
    }

    companion object {
        fun create(parent: ViewGroup): HeadingHolder {
            return HeadingHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_detail_heading, parent, false)
            )
        }
    }
}