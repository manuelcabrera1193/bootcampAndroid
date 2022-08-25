package com.bootcamp.nttdata.bootcampandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView

class BodyFragment : Fragment() {

    private var textView1: String? = null
    private var textView2: String? = null
    private var btn: String? = null
    private var edtTxt: String? = null
    lateinit var firstTxtView : AppCompatTextView
    lateinit var secondTxtView : AppCompatTextView
    lateinit var firstBtn : AppCompatButton
    lateinit var firstEdtTxt: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            textView1 = it.getString(KEY_TXT)
            textView2 = it.getString(KEY_TXT2)
            btn = it.getString(KEY_BTN)
            edtTxt = it.getString(KEY_EDT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstTxtView = requireActivity().findViewById(R.id.textViewBody1)
        textView1?.let {
            firstTxtView.text = textView1
        }
        secondTxtView = requireActivity().findViewById(R.id.textViewBody2)
        textView2?.let{
            secondTxtView.text = textView2
        }
        firstBtn = requireActivity().findViewById(R.id.btnBody)
        btn?.let{
            firstBtn.text = btn
        }
        firstEdtTxt = requireActivity().findViewById(R.id.editTextBody)
        edtTxt?.let{
            firstEdtTxt.hint = edtTxt
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_body, container, false)
    }

    companion object {

        const val KEY_TXT = "Texto 1"
        const val KEY_TXT2 = "Texto 2"
        const val KEY_BTN = "Boton"
        const val KEY_EDT = "Edit Text"

        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String, param4: String) =
            BodyFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TXT, param1)
                    putString(KEY_TXT2, param2)
                    putString(KEY_BTN, param3)
                    putString(KEY_EDT, param4)
                }
            }
    }
}