package com.sayempro.retrofitrestapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sayempro.retrofitrestapi.databinding.ItemCardBinding

class PostsAdapter(val posts: ArrayList<Post>) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    class PostsViewHolder(val adapterBinding: ItemCardBinding) : RecyclerView.ViewHolder(adapterBinding.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PostsViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(
        holder: PostsViewHolder,
        position: Int,
    ) {
        val post = posts[position]
        holder.adapterBinding.textViewId.text = post.id.toString()
        holder.adapterBinding.textViewUserId.text = post.userId.toString()
        holder.adapterBinding.textViewTitle.text = post.title
        holder.adapterBinding.textViewBody.text = post.subtitle
    }
}
