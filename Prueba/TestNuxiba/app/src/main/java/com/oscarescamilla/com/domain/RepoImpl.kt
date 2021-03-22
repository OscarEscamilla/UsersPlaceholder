package com.oscarescamilla.com.domain

import com.oscarescamilla.com.data.model.comment.Comment
import com.oscarescamilla.com.data.model.post.Post
import com.oscarescamilla.com.data.model.todo.Todo
import com.oscarescamilla.com.data.model.todo.TodoBody
import com.oscarescamilla.com.data.remote.RemoteDataSource
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.vo.Resource

class RepoImpl(private val remoteDataSource: RemoteDataSource): Repo {


    override suspend fun getUsers(): Resource<List<User>> {
        return remoteDataSource.getUsers()
    }

    override suspend fun getPostsByUser(id: Int): Resource<List<Post>> {
        return remoteDataSource.getPostsByUser(id)
    }

    override suspend fun getCommentsByPost(id: Int): Resource<List<Comment>> {
        return remoteDataSource.getCommentsByPost(id)
    }

    override suspend fun getTodosByUser(id: Int): Resource<List<Todo>> {
        return remoteDataSource.getTodosByUser(id)
    }

    override suspend fun addTodo(body: TodoBody): Resource<Todo> {
        return  remoteDataSource.addTodo(body)
    }


}