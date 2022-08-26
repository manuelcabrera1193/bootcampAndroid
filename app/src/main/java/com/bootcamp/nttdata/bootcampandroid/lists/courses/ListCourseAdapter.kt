package com.bootcamp.nttdata.bootcampandroid.lists.courses

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bootcamp.nttdata.bootcampandroid.R
import com.bootcamp.nttdata.bootcampandroid.databinding.FragmentCourseBinding
import com.bootcamp.nttdata.bootcampandroid.models.Course

interface EventsItem {
    fun deleteItem(id: Int)
}

class ListCourseAdapter(private val listener: EventsItem) :
    ListAdapter<Course, ListCourseAdapter.ViewHolder>(CoursesDiffCallback) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.fragment_course, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            binding.itemNumber.text = position.toString()
            binding.content.text = item.content
            binding.button.setOnClickListener {
                listener.deleteItem(item.id.toInt())
            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = FragmentCourseBinding.bind(view)
        override fun toString(): String {
            return super.toString() + " '" + binding.content.text + "'"
        }
    }

    object CoursesDiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }
}

