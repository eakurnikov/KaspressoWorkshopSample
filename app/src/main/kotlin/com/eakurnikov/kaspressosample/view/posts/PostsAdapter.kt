package com.eakurnikov.kaspressosample.view.posts

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eakurnikov.kaspressosample.data.model.Post

/**
 * Created by eakurnikov on 2019-12-15
 */
class PostsAdapter : RecyclerView.Adapter<PostViewHolder>() {
    var data: List<Post> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder.create(parent)

    override fun onBindViewHolder(viewHolder: PostViewHolder, position: Int): Unit =
        viewHolder.bind(position, data[position])

    override fun getItemCount(): Int = data.size
}