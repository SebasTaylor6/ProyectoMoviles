package com.proyecto.proyectomoviles.data.repository

import androidx.lifecycle.LiveData
import com.proyecto.proyectomoviles.data.User
import com.proyecto.proyectomoviles.data.dao.UserDAO

class UserRepository(private val userDAO: UserDAO) {
    val readAllData:LiveData<List<User>> = userDAO.readAllData()

    suspend fun addUser(user:User){
        return userDAO.addUser(user)
    }

    suspend fun getUser(username:String,password:String):User{
        return userDAO.getUser(username,password)
    }


}