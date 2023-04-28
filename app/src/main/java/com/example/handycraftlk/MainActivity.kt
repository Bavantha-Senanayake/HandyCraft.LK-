package com.example.handycraftlk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.handycraftlk.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(v: View){

        val intent = Intent(this, MainActivity_register::class.java)
        startActivity(intent)
        finish()
    }

    fun buttonLogin(v: View){

        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }

}