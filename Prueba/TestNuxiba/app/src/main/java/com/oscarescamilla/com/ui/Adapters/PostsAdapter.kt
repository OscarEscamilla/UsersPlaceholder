package com.oscarescamilla.com.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oscarescamilla.com.R
import com.oscarescamilla.com.base.BaseViewHolder
import com.oscarescamilla.com.data.model.post.Post
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.databinding.RowPostBinding


class PostsAdapter(private val context: Context, private val items: List<Post>, private val user: User, private val onShowCommentsClickListener: OnShowCommentsClickListener)
    : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnShowCommentsClickListener{

        fun showComments(post: Post)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_post, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(items[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Post>(itemView) {

        private val binding = RowPostBinding.bind(itemView)

        override fun bind(post: Post, position: Int) {

            Glide.with(context).load(user.image).centerCrop().into(binding.imgUser)
            binding.tvUserName.text = user.name
            binding.tvTitlePost.text = post.title
            binding.tvBodyPost.text = post.body
            binding.btnShowComments.setOnClickListener {
                onShowCommentsClickListener.showComments(post)
            }

//            binding.userRowLayout.setOnClickListener { itemClickListener.onUserClick(item) }

        }

    }

}