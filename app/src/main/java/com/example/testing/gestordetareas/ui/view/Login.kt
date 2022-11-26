package com.example.testing.gestordetareas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testing.gestordetareas.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            back.setOnClickListener {
                finish()
            }
        }
    }
}