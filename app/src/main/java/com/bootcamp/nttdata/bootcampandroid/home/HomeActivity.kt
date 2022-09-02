package com.bootcamp.nttdata.bootcampandroid.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bootcamp.nttdata.bootcampandroid.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}