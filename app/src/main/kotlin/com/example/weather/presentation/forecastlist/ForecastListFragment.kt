package com.example.weather.presentation.forecastlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.base.BaseFragment
import com.example.weather.R
import com.example.weather.databinding.FragmentForecastListBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

@AndroidEntryPoint
internal class ForecastListFragment : BaseFragment<FragmentForecastListBinding>() {

    private lateinit var cityName: String

    private val viewModel: ForecastListViewModel by viewModels()

    private lateinit var adapter: ForecastRecyclerAdapter

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentForecastListBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ForecastRecyclerAdapter { onItemClicked(it) }

        with(binding) {
            rvForecast.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvForecast.adapter = adapter

            swiperefresh.isRefreshing = true

            viewModel.forecasts.observe(viewLifecycleOwner, {
                adapter.setData(it)
                swiperefresh.isRefreshing = false
            })

            viewModel.error.observe(viewLifecycleOwner, {
                swiperefresh.isRefreshing = false
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })

            val args by navArgs<ForecastListFragmentArgs>()
            cityName = args.cityName

            with(requireActivity() as AppCompatActivity) {
                setSupportActionBar(toolbar)
                supportActionBar?.title = cityName
                setupActionBarWithNavController(NavHostFragment.findNavController(this@ForecastListFragment))
            }

            swiperefresh.setOnRefreshListener {
                viewModel.fetchForecast()
            }
        }
    }

    private fun onItemClicked(date: Long) {
        NavHostFragment.findNavController(this)
            .navigate(
                R.id.action_forecastListFragment_to_forecastDetailsFragment,
                bundleOf("cityName" to cityName, "date" to date)
            )
    }
}