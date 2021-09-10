package com.example.weather.presentation.forecastdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.weather.R
import com.example.weather.base.BaseFragment
import com.example.weather.databinding.FragmentForecastDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ForecastDetailsFragment : BaseFragment<FragmentForecastDetailsBinding>() {

    private val viewModel: ForecastDetailsViewModel by viewModels()

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentForecastDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.forecastDetails.observe(viewLifecycleOwner, { forecastDetails ->
            binding.run {
                tvTemperature.text = forecastDetails.temperature.toString()
                tvWeatherPerception.text = requireContext().getString(
                    R.string.forecast_details_feels_like,
                    forecastDetails.weatherPerception
                )
                tvWeatherCondition.text = forecastDetails.weatherCondition
                tvWeatherDescription.text = forecastDetails.weatherDescription
            }
        })

        val args by navArgs<ForecastDetailsFragmentArgs>()
        val cityName = args.cityName

        with(requireActivity() as AppCompatActivity) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = cityName
            setupActionBarWithNavController(NavHostFragment.findNavController(this@ForecastDetailsFragment))
        }

        viewModel.loadData()
    }
}