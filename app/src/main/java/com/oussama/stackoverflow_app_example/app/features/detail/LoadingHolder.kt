package com.oussama.stackoverflow_app_example.app.features.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.oussama.stackoverflow_app_example.R

class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind() {
        with(itemView) {
            findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
        }

    }

    companion object {
        fun create(parent: ViewGroup): LoadingHolder {
            return LoadingHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_progress, parent, false)
            )
        }
    }
}