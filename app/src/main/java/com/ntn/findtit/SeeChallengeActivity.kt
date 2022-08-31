package com.ntn.findtit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ntn.findtit.databinding.ActivitySeeChallengeBinding

class SeeChallengeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeeChallengeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeChallengeBinding.inflate(layoutInflater)



    }
}