package com.livecoding.android.app.ui.activity.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.livecoding.android.app.databinding.FragmentHomeBinding
import com.livecoding.android.app.ui.activity.localsorting.LocalSortingActivity
import com.livecoding.android.app.ui.activity.moviepaginated.MoviePaginatedActivity
import dagger.android.support.DaggerFragment

class HomeFragment : DaggerFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonMoviePaginated.setOnClickListener { _ ->
            MoviePaginatedActivity.startActivity(activity)
        }

        binding.buttonLocalSorting.setOnClickListener { _ ->
            LocalSortingActivity.startActivity(activity)
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}