package com.oscarescamilla.com.ui.todos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscarescamilla.com.data.model.post.Post
import com.oscarescamilla.com.data.model.todo.Todo
import com.oscarescamilla.com.data.model.todo.TodoBody
import com.oscarescamilla.com.domain.Repo
import com.oscarescamilla.com.vo.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class TodosViewModel(private val repo: Repo): ViewModel() {




    private val _todosResponse: MutableLiveData<Resource<List<Todo>>> = MutableLiveData()

    val todosResponse: LiveData<Resource<List<Todo>>>
        get() = _todosResponse


    private val _addTodoResponse: MutableLiveData<Resource<Todo>> = MutableLiveData()

    val addTodoResponse: LiveData<Resource<Todo>>
        get() = _addTodoResponse



    fun addTodo(todoBody: TodoBody) = viewModelScope.launch {
        _addTodoResponse.value = Resource.Loading()
        try {
            _addTodoResponse.value = repo.addTodo(todoBody)
        }catch (e: Exception){
            e.printStackTrace()
            e.message?.let { Log.e("fail: ", it) }
            _addTodoResponse.value = Resource.Failure(e)
        }
    }



    fun getTodos(id: Int) = viewModelScope.launch {
        _todosResponse.value = Resource.Loading()
        try {
            _todosResponse.value = repo.getTodosByUser(id)
        }catch (e: Exception){
            e.printStackTrace()
            e.message?.let { Log.e("fail: ", it) }
            _todosResponse.value = Resource.Failure(e)
        }
    }

}