package com.livecoding.android.app.ui.activity.moviepaginated.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.livecoding.android.app.databinding.MoviePaginatedFragmentBinding
import com.livecoding.android.app.ui.ext.hide
import com.livecoding.android.app.ui.ext.show
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviePaginatedFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding: MoviePaginatedFragmentBinding? = null
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
        _binding = MoviePaginatedFragmentBinding.inflate(inflater, container, false)
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

        val movieDataStateAdapter = NowPlayingStateDataLoadedAdapter()
        movieDataStateAdapter.withLoadStateFooter(NowPlayingStateLoadMoreAdapter())

        movieDataStateAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading) {
                binding.moviePaginatedContentData.nowPlayingLoadState.root.show()
                binding.moviePaginatedContentData.nowPlayingDataState.hide()
            } else {
                binding.moviePaginatedContentData.nowPlayingLoadState.root.hide()
                binding.moviePaginatedContentData.nowPlayingDataState.show()
            }
        }

        with(binding.moviePaginatedContentData.nowPlayingDataState) {
            val manager = LinearLayoutManager(super.getContext())

            layoutManager = manager
            adapter = movieDataStateAdapter

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        activity?.lifecycleScope?.launch {
            delay(5000) // Delay loading data to showcase shimmering effect
            viewModel.nowPlayingMovies.collectLatest { pagingData ->
                movieDataStateAdapter.submitData(pagingData)
            }
        }
    }

    companion object {
        fun newInstance() = MoviePaginatedFragment()
    }
}