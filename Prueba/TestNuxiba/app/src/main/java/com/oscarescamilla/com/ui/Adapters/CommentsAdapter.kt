package com.oscarescamilla.com.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oscarescamilla.com.R
import com.oscarescamilla.com.base.BaseViewHolder
import com.oscarescamilla.com.data.model.comment.Comment
import com.oscarescamilla.com.databinding.RowCommentBinding
import com.oscarescamilla.com.databinding.RowPostBinding


class CommentsAdapter(private val context: Context, private val items: List<Comment>)
    : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnCommentClickListener {

        fun onCommentClick(comment: Comment)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_comment, parent, false)
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

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Comment>(itemView) {

        private val binding = RowCommentBinding.bind(itemView)

        override fun bind(comment: Comment, position: Int) {


            binding.tvUserMail.text = comment.email
            binding.bodyComment.text = comment.body

        }


    }

}