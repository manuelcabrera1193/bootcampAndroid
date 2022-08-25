package com.bootcamp.nttdata.bootcampandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.titleFragment, HeaderFragment.newInstance("-- Nuevo Titulo --"))
        fragmentTransaction.commit()


        /**
         * Tarea:
         *
         * Crear un fragmento que simule el body de la pantalla
         *
         * debe considerar lo siguiente :
         *
         * El layout raiz se puede usar LinearLayout o ConstraintLayout
         * Debe tener dos textView, un botón y un edittext
         *
         */

    }
}