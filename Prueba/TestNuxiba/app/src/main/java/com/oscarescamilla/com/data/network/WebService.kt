package com.oscarescamilla.com.data.network


import com.oscarescamilla.com.data.model.comment.CommetsResponse
import com.oscarescamilla.com.data.model.post.PostsResponse
import com.oscarescamilla.com.data.model.todo.Todo
import com.oscarescamilla.com.data.model.todo.TodoBody
import com.oscarescamilla.com.data.model.todo.TodosResponse
import com.oscarescamilla.com.data.model.user.UsersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


// suspend: indica que esta funcion hace una llamada a aunj servidor

interface WebService {

//    https://jsonplaceholder.typicode.com/posts
//    https://jsonplaceholder.typicode.com/users
//    https://jsonplaceholder.typicode.com/users/(userId)/posts
//    https://jsonplaceholder.typicode.com/post/(postId)/comments
//    https://jsonplaceholder.typicode.com/users/(userId)/todos

    @GET("users")
    suspend fun getUsers(): UsersResponse

    @GET("users/{id}/posts")
    suspend fun getPostsByUser(@Path("id") id: Int): PostsResponse

    @GET("post/{id}/comments")
    suspend fun getCommentsByPost(@Path("id") id: Int): CommetsResponse

    @GET("users/{id}/todos")
    suspend fun getTodosByUser(@Path("id") id: Int): TodosResponse


    @POST("posts")
    suspend fun addTodo(@Body todoBody: TodoBody): Todo

}