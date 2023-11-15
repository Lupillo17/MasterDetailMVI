package com.example.masterdetailmvi.presentation.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.masterdetailmvi.databinding.ItemListSchoolBinding
import com.example.masterdetailmvi.domain.model.School
import com.example.masterdetailmvi.presentation.utils.SchoolLibraryCallback

class SchoolsLibraryAdapter(
    private val onSchoolClicked: (String) -> Unit
) :
    PagingDataAdapter<School, SchoolsLibraryAdapter.SchoolLibraryViewHolder>(SchoolLibraryCallback()) {
    private lateinit var binding: ItemListSchoolBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolLibraryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemListSchoolBinding.inflate(layoutInflater, parent, false)
        return SchoolLibraryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SchoolLibraryViewHolder, position: Int) {
        getItem(position)?.let { school ->
            holder.render(
                onSchoolClicked = onSchoolClicked,
                school = school
            )
        }
    }


    inner class SchoolLibraryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ItemListSchoolBinding? = DataBindingUtil.bind(view)

        fun render(
            onSchoolClicked: (String) -> Unit,
            school: School
        ) {
            binding?.let {
                it.cardView.setOnClickListener { onSchoolClicked.invoke(school.id) }
                it.school = school
            }
        }
    }
}