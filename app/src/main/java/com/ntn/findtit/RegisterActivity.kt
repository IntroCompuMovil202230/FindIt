package com.ntn.findtit

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.ntn.findtit.databinding.ActivityRegisterBinding
import com.parse.ParseUser


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.registerLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRegister.setOnClickListener{
            val user = ParseUser()

            user.setUsername(binding.userField.text.toString())
            user.setPassword(binding.passField.text.toString())
            user.setEmail(binding.emailField.text.toString())

            user.signUpInBackground { e ->
                if (e == null) {
                    Log.i(TAG, "Attempt to register on parse successfully")
                    Toast.makeText(applicationContext, "Ok", Toast.LENGTH_LONG).show()
                } else {
                    Log.i(TAG, "Attempt to register on parse failed")
                    Toast.makeText(
                        applicationContext,
                        "No Ok" + e.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
