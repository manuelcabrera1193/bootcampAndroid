package com.bootcamp.nttdata.bootcampandroid.lists.persons

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.nttdata.bootcampandroid.R
import com.bootcamp.nttdata.bootcampandroid.databinding.ItemPersonBinding
import com.bootcamp.nttdata.bootcampandroid.models.Person

class ListPersonAdapter(private val persons: List<Person>) :
    RecyclerView.Adapter<ListPersonAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.fragment_item_person, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = persons[position]
        with(holder) {
            binding.idView.text = position.toString()
            binding.nameView.text = person.name
            binding.lastnameView.text = person.lastname
        }
    }

    override fun getItemCount(): Int = persons.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPersonBinding.bind(view)
    }

}

