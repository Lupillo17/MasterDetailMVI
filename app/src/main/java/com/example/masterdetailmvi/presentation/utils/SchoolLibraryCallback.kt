package com.example.masterdetailmvi.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.masterdetailmvi.domain.model.School

class SchoolLibraryCallback: DiffUtil.ItemCallback<School>() {
        override fun areItemsTheSame(oldItem: School, newItem: School): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: School, newItem: School): Boolean =
            oldItem.name == newItem.name
                    && oldItem.description == newItem.description

}