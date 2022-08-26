package com.bootcamp.nttdata.bootcampandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.titleFragment, HeaderFragment())
        fragmentTransaction.add(R.id.bodyFragment, BodyFragment())
        fragmentTransaction.commit()

    }
}