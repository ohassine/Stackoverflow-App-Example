package com.oussama.stackoverflow_app_example.app.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oussama.entities.State
import com.oussama.stackoverflow_app_example.R

class FooterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(state: State) {
        itemView.findViewById<ProgressBar>(R.id.progress_bar).visibility =
            if (state == State.LOADING) View.VISIBLE else View.GONE
        itemView.findViewById<TextView>(R.id.txt_error).visibility =
            if (state == State.ERROR) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): FooterHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_footer, parent, false)
            view.findViewById<TextView>(R.id.txt_error).setOnClickListener {
                retry()
            }
            return FooterHolder(view)
        }
    }
}