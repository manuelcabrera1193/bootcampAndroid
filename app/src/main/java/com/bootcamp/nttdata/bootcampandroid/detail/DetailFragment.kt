package com.bootcamp.nttdata.bootcampandroid.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bootcamp.nttdata.bootcampandroid.R
import com.bootcamp.nttdata.bootcampandroid.databinding.FragmentDetailBinding
import com.bootcamp.nttdata.bootcampandroid.states.PersonState
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel

    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        viewModel.personState.observe(viewLifecycleOwner, Observer(::getState))
        /**
         * Condicion que aplique
         */
        binding.btnSuccess.setOnClickListener {
            viewModel.validateCredentials("Manuel", "123")
        }

        binding.btnError.setOnClickListener {
            viewModel.validateCredentials("Manuel", "1fgg23")
        }
    }

    private fun getState(state: PersonState) {
        when (state) {
            PersonState.Loading -> {
                binding.btnError.isEnabled = false
                binding.btnSuccess.isEnabled = false
                binding.loadingView.isVisible = true
                Log.i("TAG", "PersonState.Loading")
            }
            is PersonState.Success -> {
                binding.btnError.isEnabled = true
                binding.btnSuccess.isEnabled = true
                binding.loadingView.isVisible = false

                val toast = Toast.makeText(
                    requireContext(),
                    "Success Bienvenido :  ${state.person.name}",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
                Log.i("TAG", "PersonState.Success")
            }
            is PersonState.Error -> {
                binding.btnError.isEnabled = true
                binding.btnSuccess.isEnabled = true
                binding.loadingView.isVisible = false
                //Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                var messageError : String = ""

                messageError = when (state.error.code) {
                    401 -> "Error de token ${state.error.message}"
                    404 -> "Error ${state.error.message}"
                    500 -> "Error de servidor : ${state.error.message}"
                    else -> "Error desconocido"
                }

                val snackbar =
                    Snackbar.make(requireView(), messageError, Snackbar.LENGTH_LONG)

                snackbar.setBackgroundTint(resources.getColor(R.color.red))
                snackbar.show()
                Log.i("TAG", "PersonState.Error")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}