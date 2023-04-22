package com.proyecto.proyectomoviles.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username:String,
    val password:String,
    val role_id:Int
)