package com.example.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(private val lists: ArrayList<TaskList>, val clickListener: TodoListClickListener): RecyclerView.Adapter<TodoListVeiwHolder>() {

    interface TodoListClickListener {
        fun listItemClicked(list: TaskList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListVeiwHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false)
        return TodoListVeiwHolder(view)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: TodoListVeiwHolder, position: Int) {

        holder.listPositionTextView.text = (position + 1).toString()
        holder.listTitleTextView.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size-1)
    }
}