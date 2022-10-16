package com.ntn.findtit

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ntn.findtit.databinding.ActivityMainBinding
import com.parse.LogInCallback
import com.parse.ParseUser


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            loginUser(binding.userField.text.toString(),binding.passlogin.text.toString())
        }
        binding.loginRegister.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }



    }

    private fun loginUser(username:String,pass:String) {
        ParseUser.logInInBackground(username, pass) { user, e ->
            if (user != null) {
                // Hooray! The user is logged in.
                Log.i(ContentValues.TAG, "Attempt to login on parse successfully")
                Toast.makeText(applicationContext, "Ok", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, PrincipalActivity::class.java)
                startActivity(intent)
            } else {
                // Signup failed. Look at the ParseException to see what happened.
                Log.i(ContentValues.TAG, "Attempt to login on parse failed")
                Toast.makeText(
                    applicationContext,
                    "No Ok" + e.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}