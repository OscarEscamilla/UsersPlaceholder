package com.oscarescamilla.com.ui.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.oscarescamilla.com.R
import com.oscarescamilla.com.base.BaseViewHolder
import com.oscarescamilla.com.data.model.todo.Todo
import com.oscarescamilla.com.databinding.RowPostBinding
import com.oscarescamilla.com.databinding.RowTodoBinding
import kotlinx.android.synthetic.main.fragment_todos.view.*


class TodosAdapter(private val context: Context, private val items: MutableList<Todo>)
    : RecyclerView.Adapter<BaseViewHolder<*>>() {



    init {
        items.reverse()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_todo, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return items.size
    }


    fun addTodo(todo: Todo){
        items.reverse()
        items.add(todo)
        items.reverse()
        notifyDataSetChanged()


    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(items[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Todo>(itemView) {

        private val binding = RowTodoBinding.bind(itemView)

        override fun bind(todo: Todo, position: Int) {

            binding.tvTitleTodo.text = "${todo.id} | ${todo.title}"
            binding.ckCompletedTodo.isChecked = todo.completed

            binding.ckCompletedTodo.setOnClickListener {
                if (items[position].completed){
                    items[position].completed = false
                }else if(!items[position].completed){
                    items[position].completed = true
                }

                Log.e("item",  items[position].toString())
            }



        }



    }

}