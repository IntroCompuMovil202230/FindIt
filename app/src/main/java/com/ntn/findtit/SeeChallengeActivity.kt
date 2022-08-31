package com.ntn.findtit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ntn.findtit.databinding.ActivityMainBinding

class SeeChallengeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

    }
}