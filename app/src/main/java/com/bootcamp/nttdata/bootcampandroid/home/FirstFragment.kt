package com.bootcamp.nttdata.bootcampandroid.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bootcamp.nttdata.bootcampandroid.R
import com.bootcamp.nttdata.bootcampandroid.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goListGenericFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_itemFragment)
        }
        binding.goListPersonFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_listPersonFragment)
        }
        binding.goListCoursesFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_coursesFragment)
        }
        binding.goDetailFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_detailFragment)
        }
        binding.goListDogs.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_dogsFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}