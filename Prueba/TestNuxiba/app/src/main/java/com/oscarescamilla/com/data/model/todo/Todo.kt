package com.oscarescamilla.com.data.model.todo

data class Todo(
    var completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)