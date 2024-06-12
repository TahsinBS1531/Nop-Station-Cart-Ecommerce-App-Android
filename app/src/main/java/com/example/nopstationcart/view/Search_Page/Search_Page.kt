package com.example.nopstationcart.view.Search_Page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nopstationcart.R
import com.example.nopstationcart.databinding.FragmentSearchPageBinding


class Search_Page : Fragment() {
    private lateinit var binding:FragmentSearchPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search__page, container, false)
        binding = FragmentSearchPageBinding.bind(view)
        val searchBar = binding.searchBarProducts


        return binding.root
    }

}