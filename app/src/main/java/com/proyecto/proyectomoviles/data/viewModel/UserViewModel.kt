package com.proyecto.proyectomoviles.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.proyecto.proyectomoviles.data.User
import com.proyecto.proyectomoviles.data.database.UserDatabase
import com.proyecto.proyectomoviles.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
    private val readAllData: MutableLiveData<List<User>>
    private val repository : UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = MutableLiveData()
    }

    fun addUser(user: User){
        viewModelScope.launch (Dispatchers.IO){ repository.addUser(user) }
    }

    fun getAllUsersObservers():MutableLiveData<List<User>>{
        return readAllData
    }

    fun getAllUsers(){
        val userDao = UserDatabase.getDatabase(application).userDao()
        val list = userDao?.readAllData()
        readAllData.postValue(list)
    }

    fun getUser(username:String,password:String): User? {
        val users: List<User>? = readAllData.value
        for (user in readAllData.value!!){
            if (user.username == username && user.password == password){
                return user
            }
        }
        return null

    }
}