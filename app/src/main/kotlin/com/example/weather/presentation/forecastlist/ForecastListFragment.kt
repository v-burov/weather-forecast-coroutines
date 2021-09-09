package com.example.weather.presentation.forecastlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.base.BaseFragment
import com.example.weather.R
import com.example.weather.databinding.FragmentForecastListBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

@AndroidEntryPoint
internal class ForecastListFragment : BaseFragment<FragmentForecastListBinding>() {

    companion object {
        private const val PARAMS_CITY_NAME = "params_city_name"
        private const val PARAMS_DATE = "params_date"
    }

    private val viewModel: ForecastListViewModel by viewModels()

    private lateinit var adapter: ForecastRecyclerAdapter

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentForecastListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ForecastRecyclerAdapter { onItemClicked(it) }
        binding.rvForecast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvForecast.adapter = adapter

        binding.swiperefresh.isRefreshing = true

        viewModel.forecasts.observe(viewLifecycleOwner, {
            adapter.setData(it)
            binding.swiperefresh.isRefreshing = false
        })

        viewModel.error.observe(viewLifecycleOwner, {
            binding.swiperefresh.isRefreshing = false
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        val cityName = requireArguments().getString(PARAMS_CITY_NAME)
            ?: throw IllegalStateException("City name cannot be empty")

        viewModel.fetchForecast(cityName)

        binding.swiperefresh.setOnRefreshListener {
            viewModel.fetchForecast(cityName)
        }
    }

    private fun onItemClicked(date: Long) {
        val bundle = Bundle()
        bundle.putLong(PARAMS_DATE, date)

        NavHostFragment.findNavController(this)
            .navigate(R.id.action_forecastListFragment_to_forecastDetailsFragment, bundle)
    }
}