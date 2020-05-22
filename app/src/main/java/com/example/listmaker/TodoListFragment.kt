package com.example.listmaker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoListFragment : Fragment(), TodoListAdapter.TodoListClickListener {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var listDataManager: ListDataManager


    interface OnFragmentInteractionListener {
        fun onTodoListClicked(list:TaskList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lists = listDataManager.readLists()
        todoListRecyclerView = view.findViewById(R.id.lists_recyclerview)
        todoListRecyclerView.layoutManager = LinearLayoutManager(activity)
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    companion object {
        fun newInstance(): TodoListFragment {
            return TodoListFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            listDataManager = ListDataManager(context)
        }
    }

    override fun listItemClicked(list: TaskList) {
        listener?.onTodoListClicked(list)
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val todoAdapter = todoListRecyclerView.adapter as TodoListAdapter
        todoAdapter.addList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        val lists = listDataManager.readLists()
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)
    }
}
