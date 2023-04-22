package com.proyecto.proyectomoviles.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.proyecto.proyectomoviles.data.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user ORDER BY id ASC")
    suspend fun readAllData():List<User>?

    @Query("SELECT * FROM user WHERE username=:username AND password=:password")
    suspend fun getUser(username:String,password:String):User?

    @Delete
    fun deleteUser(user:User?)

}