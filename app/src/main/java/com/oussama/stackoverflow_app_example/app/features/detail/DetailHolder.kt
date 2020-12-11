package com.oussama.stackoverflow_app_example.app.features.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oussama.entities.Answer
import com.oussama.entities.Question
import com.oussama.stackoverflow_app_example.R

class DetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindQuestion(item: Question,  clickListener: (String) -> Unit) {
        with(itemView) {
            findViewById<TextView>(R.id.itemTitle).text = item.title
            findViewById<TextView>(R.id.itemPoints).text = item.score.toString() + "points"
            findViewById<TextView>(R.id.itemViewed).text = "Viewed :" + item.viewCount.toString()
            setOnClickListener { clickListener(item.link) }
        }
    }

    fun bindAnswer(item: Answer, clickListener: (String) -> Unit) {
        with(itemView) {
            findViewById<TextView>(R.id.itemTitle).text = item.questionTitle
            findViewById<TextView>(R.id.itemPoints).text = item.score.toString() + " points"
            findViewById<TextView>(R.id.itemViewed).visibility = View.GONE
            setOnClickListener { clickListener("https://stackoverflow.com/a/${item.answerId}")}
        }
    }

    companion object {
        fun create(parent: ViewGroup): DetailHolder {
            return DetailHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_detail_user, parent, false)
            )
        }
    }

}