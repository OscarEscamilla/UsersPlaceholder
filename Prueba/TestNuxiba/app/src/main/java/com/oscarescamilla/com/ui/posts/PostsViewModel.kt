package com.oscarescamilla.com.ui.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscarescamilla.com.data.model.comment.Comment
import com.oscarescamilla.com.data.model.post.Post
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.domain.Repo
import com.oscarescamilla.com.vo.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class PostsViewModel(private val repo: Repo): ViewModel() {



    private val _postsResponse: MutableLiveData<Resource<List<Post>>> = MutableLiveData()

    val postsResponse: LiveData<Resource<List<Post>>>
        get() = _postsResponse



    fun getPosts(id: Int) = viewModelScope.launch {
        _postsResponse.value = Resource.Loading()
        try {
            _postsResponse.value = repo.getPostsByUser(id)
        }catch (e: Exception){
            e.printStackTrace()
            e.message?.let { Log.e("fail: ", it) }
            _postsResponse.value = Resource.Failure(e)
        }
    }

    private val _commentsResponse: MutableLiveData<Resource<List<Comment>>> = MutableLiveData()

    val commentsResponse: LiveData<Resource<List<Comment>>>
        get() = _commentsResponse



    fun getCommentsByPost(id: Int) = viewModelScope.launch {
        _commentsResponse.value = Resource.Loading()
        try {
            _commentsResponse.value = repo.getCommentsByPost(id)
        }catch (e: Exception){
            e.printStackTrace()
            e.message?.let { Log.e("fail: ", it) }
            _commentsResponse.value = Resource.Failure(e)
        }
    }


}