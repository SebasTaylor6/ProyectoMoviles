package com.proyecto.proyectomoviles.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proyecto.proyectomoviles.data.User
import com.proyecto.proyectomoviles.data.dao.UserDAO

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao():UserDAO

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context):UserDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "database.db"
                ).allowMainThreadQueries().build()
                (1..2).forEach{
                    lifecycleScope.launch{

                    }
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}