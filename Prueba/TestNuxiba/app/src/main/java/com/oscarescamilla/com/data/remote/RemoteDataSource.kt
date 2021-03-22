package com.oscarescamilla.com.data.remote

import com.oscarescamilla.com.data.model.comment.Comment
import com.oscarescamilla.com.data.model.post.Post
import com.oscarescamilla.com.data.model.todo.Todo
import com.oscarescamilla.com.data.model.todo.TodoBody
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.vo.Resource
import com.oscarescamilla.com.data.network.RetrofitClient

class RemoteDataSource {



    suspend fun getUsers(): Resource.Success<List<User>>{
        return Resource.Success(RetrofitClient.webService.getUsers())
    }

    suspend fun getPostsByUser(id: Int): Resource.Success<List<Post>>{
        return Resource.Success(RetrofitClient.webService.getPostsByUser(id))
    }


    suspend fun getCommentsByPost(id: Int): Resource.Success<List<Comment>>{
        return Resource.Success(RetrofitClient.webService.getCommentsByPost(id))
    }


    suspend fun getTodosByUser(id: Int): Resource.Success<List<Todo>>{
        return Resource.Success(RetrofitClient.webService.getTodosByUser(id))
    }


    suspend fun addTodo(body: TodoBody): Resource.Success<Todo>{
        return Resource.Success(RetrofitClient.webService.addTodo(body))
    }



}