package com.livecoding.android.app.ui.activity.moviepaginated.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.livecoding.android.app.databinding.FragmentMoviePaginatedBinding
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviePaginatedFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding: FragmentMoviePaginatedBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MoviePaginatedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviePaginatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MoviePaginatedViewModel::class.java)

        val movieDataStateAdapter = MovieDataStateAdapter()
        movieDataStateAdapter.withLoadStateFooter(MovieLoadStateAdapter())

        with(binding.movieContentData.rvMovieList) {
            val manager = LinearLayoutManager(super.getContext())

            layoutManager = manager
            adapter = movieDataStateAdapter

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        activity?.lifecycleScope?.launch {
            viewModel.nowPlayingMovies.collectLatest { pagingData ->
                movieDataStateAdapter.submitData(pagingData)
            }
        }
    }

    companion object {
        fun newInstance() = MoviePaginatedFragment()
    }
}