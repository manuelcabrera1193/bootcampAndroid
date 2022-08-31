package com.bootcamp.nttdata.bootcampandroid.dogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.nttdata.bootcampandroid.databinding.FragmentDogsBinding

class DogsFragment : Fragment() {

    private lateinit var viewModel: DogsViewModel

    private var _binding: FragmentDogsBinding? = null

    private val binding get() = _binding!!


    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel = ViewModelProvider(this)[DogsViewModel::class.java]
        viewModel.dogsState.observe(viewLifecycleOwner, Observer(::dogsEvents))
    }

    private fun dogsEvents(dogsState: DogsState) {

        when (dogsState) {
            is DogsState.Error -> Toast.makeText(
                requireContext(),
                "Ocurrio un error",
                Toast.LENGTH_LONG
            ).show()
            DogsState.Loading -> Toast.makeText(
                requireContext(),
                "Cargando .... ",
                Toast.LENGTH_LONG
            ).show()
            is DogsState.Success -> {
                val images = dogsState.dogs ?: emptyList()
                dogImages.clear()
                dogImages.addAll(images)
                adapter.notifyDataSetChanged()
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDogs.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

