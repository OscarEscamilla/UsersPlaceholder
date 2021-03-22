package com.oscarescamilla.com.ui.users

import android.util.Log
import androidx.lifecycle.*
import com.oscarescamilla.com.data.model.user.User
import com.oscarescamilla.com.domain.Repo
import com.oscarescamilla.com.vo.Resource
import kotlinx.coroutines.launch
import java.lang.Exception


class UsersViewModel(private val repo: Repo): ViewModel() {




    private val _usersResponse: MutableLiveData<Resource<List<User>>> = MutableLiveData()

    val usersResponse: LiveData<Resource<List<User>>>
        get() = _usersResponse




    init {
       getUsers()
    }


    fun getUsers() = viewModelScope.launch {
        _usersResponse.value = Resource.Loading()
        try {
            _usersResponse.value = repo.getUsers()
        }catch (e: Exception){
            e.printStackTrace()
            e.message?.let { Log.e("fail: ", it) }
            _usersResponse.value = Resource.Failure(e)
        }
    }

}