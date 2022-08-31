package com.ntn.findtit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ntn.findtit.databinding.ActivityPreviewChallengeBinding

class PreviewChallengeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewChallengeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_challenge)

        val button = binding.endPreviewChallengeButton
        button.setOnClickListener {
            finish()
        }

    }
}