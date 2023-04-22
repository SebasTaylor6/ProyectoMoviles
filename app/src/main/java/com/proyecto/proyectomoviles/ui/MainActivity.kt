package com.proyecto.proyectomoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.proyecto.proyectomoviles.R
import com.proyecto.proyectomoviles.data.User
import com.proyecto.proyectomoviles.data.database.UserDatabase
import com.proyecto.proyectomoviles.data.viewModel.UserViewModel
import com.proyecto.proyectomoviles.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var username:EditText
    private lateinit var password:EditText
    private lateinit var exit:Button
    private lateinit var login:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = findViewById(R.id.editTextUserName)
        password = findViewById(R.id.editTextTextPassword)
        exit = findViewById(R.id.Exit)
        login = findViewById(R.id.logInBtn)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        exit.setOnClickListener {
            finish()
            exitProcess(0)
        }

        login.setOnClickListener {
            loginValidation(username.text.toString(),password.text.toString())
        }
    }

    private fun loginValidation(username:String,password:String){
        if(username.isEmpty()||password.isEmpty()){
            Toast.makeText(applicationContext,"No dejes campos vacios",Toast.LENGTH_SHORT).show()
        } else{
          val user = userViewModel.getUser(username,password)
            if (user==null){
                Toast.makeText(applicationContext,"Credenciales Invalidas",Toast.LENGTH_SHORT).show()
            }else{
                roleValidation(user)
            }
        }

    }

    private fun roleValidation(user:User){
        if(user.role_id == 1){
            Toast.makeText(applicationContext,"Welcome Admin",Toast.LENGTH_SHORT).show()

        }
    }
}