package com.bootcamp.nttdata.bootcampandroid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class BodyFragment : Fragment() {

    private lateinit var firstBtn: AppCompatButton
    private lateinit var firstEdtTxt: AppCompatEditText
    private lateinit var secondEdtTxt: AppCompatEditText


    private lateinit var preferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences =
            requireContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        firstEdtTxt = requireActivity().findViewById(R.id.editTextBody1)
        secondEdtTxt = requireActivity().findViewById(R.id.editTextBody2)
        firstBtn = requireActivity().findViewById(R.id.btnBody)
        firstBtn.setOnClickListener {
            login()
        }


        val isLogged = preferences.getBoolean("isLogged", false)
        if (isLogged) {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_body, container, false)
    }


    private fun login() {
        val passwordValid = secondEdtTxt.text?.toString().equals("123")
        val isUser = firstEdtTxt.text?.toString().equals("user")
        val isAdmin = firstEdtTxt.text?.toString().equals("admin")

        Log.i("edit1", "${firstEdtTxt.text}   *********************")
        Log.i("edit1", "${secondEdtTxt.text}  *********************")


        Log.i("edit1", "$isUser *********************")
        Log.i("edit1", "$isAdmin  *********************")
        Log.i("edit1", "$passwordValid  *********************")

        var intent: Intent

        if (isAdmin && passwordValid) {
            intent = Intent(requireContext(), AdminActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        } else if (isUser && passwordValid) {
            with(preferences.edit()) {
                putBoolean("isLogged", true)
                apply()
            }
            intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        } else {
            Toast.makeText(requireContext(), "Usuario o contraseña inválida", Toast.LENGTH_LONG)
                .show()
        }
    }

}