package com.oscarescamilla.com.data.model.todo

data class TodoBody(
    val completed: Boolean,
    val title: String,
    val userId: Int
)