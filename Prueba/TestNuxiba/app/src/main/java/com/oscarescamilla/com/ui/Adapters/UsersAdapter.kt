package com.oscarescamilla.com.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oscarescamilla.com.R
import com.oscarescamilla.com.base.BaseViewHolder
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.databinding.RowUserBinding


class UsersAdapter(private val context: Context, private val items: List<User>, private val itemClickListener: OnUserClickListener)
    : RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnUserClickListener{

        fun onUserClick(user: User)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return  MainViewHolder(LayoutInflater.from(context).inflate( R.layout.row_user, parent, false))
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(items[position], position)
        }
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<User>(itemView){

        private val binding = RowUserBinding.bind(itemView)

        override fun bind(item: User, position: Int) {
            // load image in img_drink with synthetic anotation
            item.image = "https://randomuser.me/api/portraits/men/${position}.jpg"
            Glide.with(context).load(item.image).centerCrop().into(binding.imgUser)
            binding.tvUserName.text = item.name
            binding.tvUserDescription.text = item.email

            binding.userRowLayout.setOnClickListener { itemClickListener.onUserClick(item) }

        }

    }

}