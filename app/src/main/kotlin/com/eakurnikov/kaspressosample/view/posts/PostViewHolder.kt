package com.eakurnikov.kaspressosample.view.posts

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.data.model.Post

/**
 * Created by eakurnikov on 2019-12-15
 */
class PostViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(parent: ViewGroup) = PostViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        )
    }

    var item: LinearLayout = itemView.findViewById(R.id.item_post)
    var login: TextView = itemView.findViewById(R.id.tv_post_title)
    var password: TextView = itemView.findViewById(R.id.tv_post_body)

    fun bind(position: Int, post: Post) {
        item.setBackgroundColor(if (position % 2 == 0) Color.WHITE else Color.GRAY)
        login.text = post.title
        password.text = post.body
    }
}