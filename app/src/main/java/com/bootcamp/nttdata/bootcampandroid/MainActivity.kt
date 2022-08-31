package com.bootcamp.nttdata.bootcampandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.bootcamp.nttdata.bootcampandroid.Home.Home

class MainActivity : AppCompatActivity() {
    private lateinit var button: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button1)
        button.setOnClickListener{
            val intent = Intent(this,Home::class.java)
            startActivity(intent)

        }





    }
}