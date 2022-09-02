package com.bootcamp.nttdata.bootcampandroid.lists.persons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.nttdata.bootcampandroid.databinding.FragmentListPersonBinding
import com.bootcamp.nttdata.bootcampandroid.models.Person

class ListPersonFragment : Fragment() {

    private var _binding: FragmentListPersonBinding? = null

    private val binding get() = _binding!!


    private lateinit var personAdapter: ListPersonAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val persons = mutableListOf<Person>()
        for (item in 1..50) {
            persons.add(Person(item, "manuel", "cabrera"))
        }
        personAdapter = ListPersonAdapter(persons)
        linearLayoutManager = LinearLayoutManager(requireContext())

        binding.recyclerListPersons.apply {
            layoutManager = linearLayoutManager
            adapter = personAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}