package com.ntn.findtit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val b = findViewById<Button>(R.id.boton)
        b.setOnClickListener { view ->
            val intent = Intent(view!!.context, MyChallengesActivity::class.java)
            startActivity(intent)
        }


    }

}
