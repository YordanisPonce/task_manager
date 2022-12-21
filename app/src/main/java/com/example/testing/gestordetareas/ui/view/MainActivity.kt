package com.example.testing.gestordetareas.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testing.gestordetareas.R
import com.example.testing.gestordetareas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var time = 2000L
    private var currenTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            login.setOnClickListener {
                val i = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(i)
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onBackPressed() {
        if (time + currenTime > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            currenTime = System.currentTimeMillis()
            Snackbar.make(binding.root, "Pulse de nuevo para salir", Snackbar.LENGTH_LONG).apply {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextColor(getColorStateList(R.color.white))
                    setBackgroundTintList(getColorStateList(R.color.blue_200))
                }
                show()
            }

        }
    }

}