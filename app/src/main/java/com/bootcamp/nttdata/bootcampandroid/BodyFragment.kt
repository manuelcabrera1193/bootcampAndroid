package com.bootcamp.nttdata.bootcampandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class BodyFragment : Fragment() {

    lateinit var firstBtn : AppCompatButton
    lateinit var firstEdtTxt: AppCompatEditText
    lateinit var secondEdtTxt: AppCompatEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstEdtTxt = requireActivity().findViewById(R.id.editTextBody1)
        secondEdtTxt = requireActivity().findViewById(R.id.editTextBody2)
        firstBtn = requireActivity().findViewById(R.id.btnBody)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_body, container, false)
    }

}