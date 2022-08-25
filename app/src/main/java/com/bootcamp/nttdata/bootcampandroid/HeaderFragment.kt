package com.bootcamp.nttdata.bootcampandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView

class HeaderFragment : Fragment() {
    private var titulo: String? = null
    lateinit var titleTextView : AppCompatTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            titulo = it.getString(KEY_TITLE)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleTextView = requireActivity().findViewById(R.id.titleTextView)
        titulo?.let {
            titleTextView.text = titulo
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_header, container, false)
    }

    companion object {

        const val KEY_TITLE = "TITULO"

        @JvmStatic
        fun newInstance(title: String) =
            HeaderFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, title)

                }
            }
    }
}