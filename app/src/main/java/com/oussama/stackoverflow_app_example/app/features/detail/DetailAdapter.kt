package com.oussama.stackoverflow_app_example.app.features.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oussama.entities.Answer
import com.oussama.entities.Question
import com.oussama.entities.State
import com.oussama.entities.User

const val DATA_VIEW_TYPE = 1
const val HEADING_VIEW_TYPE = 2
const val USER_VIEW_TYPE = 3
const val LOADING_VIEW_TYPE = 4

class DetailAdapter(private val clickListener : (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Any> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DATA_VIEW_TYPE -> DetailHolder.create(parent)
            HEADING_VIEW_TYPE -> HeadingHolder.create(parent)
            USER_VIEW_TYPE -> UserProfileHolder.create(parent)
            else -> LoadingHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            DATA_VIEW_TYPE -> {
                if (items[position] is Question)
                    (holder as DetailHolder).bindQuestion((items[position] as Question), clickListener)
                else
                    (holder as DetailHolder).bindAnswer((items[position] as Answer), clickListener)
            }
            HEADING_VIEW_TYPE -> {
                (holder as HeadingHolder).bind(items[position].toString())
            }
            USER_VIEW_TYPE -> {
                (holder as UserProfileHolder).bind(items[position] as User)
            }
            else -> (holder as LoadingHolder).bind()
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is String -> HEADING_VIEW_TYPE
            is User -> USER_VIEW_TYPE
            is State -> LOADING_VIEW_TYPE
            else -> DATA_VIEW_TYPE
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(newItems: MutableList<Any>) {
        this.items = newItems
        notifyDataSetChanged()
    }

}