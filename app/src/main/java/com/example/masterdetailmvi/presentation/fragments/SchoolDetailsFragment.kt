package com.example.masterdetailmvi.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.masterdetailmvi.R


class SchoolDetailsFragment : Fragment() {
    val args: SchoolDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        args.schoolId
        return inflater.inflate(R.layout.fragment_school_details, container, false)
    }

}