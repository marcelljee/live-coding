package com.livecoding.android.app.ui.activity.localsorting.fragment

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.livecoding.android.app.R
import com.livecoding.android.app.databinding.FragmentLocalSortingBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LocalSortingFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding: FragmentLocalSortingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LocalSortingViewModel

    private lateinit var localSortingDataStateAdapter: ShipmentDataStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocalSortingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_local_sorting, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_date_nearest -> {
                localSortingDataStateAdapter.sortItemByDateNearest()
                return true
            }
            R.id.action_date_farthest -> {
                localSortingDataStateAdapter.sortItemByDateFarthest()
                return true
            }
            R.id.action_price_lowest -> {
                localSortingDataStateAdapter.sortItemByPriceLowest()
                return true
            }
            R.id.action_price_highest -> {
                localSortingDataStateAdapter.sortItemByPriceHighest()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LocalSortingViewModel::class.java)

        localSortingDataStateAdapter = ShipmentDataStateAdapter(viewModel.getShipments())

        with(binding.rvShipmentList) {
            val manager = LinearLayoutManager(super.getContext())

            layoutManager = manager
            adapter = localSortingDataStateAdapter

            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    companion object {
        fun newInstance() = LocalSortingFragment()
    }
}