package com.oscarescamilla.com.domain


import com.oscarescamilla.com.data.model.comment.Comment
import com.oscarescamilla.com.data.model.post.Post
import com.oscarescamilla.com.data.model.todo.Todo
import com.oscarescamilla.com.data.model.todo.TodoBody
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.vo.Resource

interface Repo {

    suspend fun getUsers(): Resource<List<User>>

    suspend fun getPostsByUser(id: Int): Resource<List<Post>>

    suspend fun getCommentsByPost(id: Int): Resource<List<Comment>>

    suspend fun getTodosByUser(id: Int): Resource<List<Todo>>

    suspend fun addTodo(todoBody: TodoBody): Resource<Todo>

}