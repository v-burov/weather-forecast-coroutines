package com.example.weather.presentation.forecastsearch

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.weather.databinding.FragmentSearchBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.weather.base.BaseFragment
import com.example.weather.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ForecastSearchFragment : BaseFragment<FragmentSearchBinding>() {

    companion object {
        private const val PARAMS_CITY_NAME = "params_city_name"
    }

    private val viewModel: ForecastSearchViewModel by viewModels()

    override fun bindView(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.success.observe(viewLifecycleOwner, {
            hideKeyboard()
            navigateToForecastList(it)
            binding.etSearch.text = null
        })

        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        with(binding) {
            etSearch.requestFocus()

            btnLookup.setOnClickListener {
                viewModel.validateCityName(etSearch.text.toString())
            }
        }
    }

    private fun navigateToForecastList(cityName: String) {
        val bundle = Bundle()
        bundle.putString(PARAMS_CITY_NAME, cityName)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_searchFragment_to_forecastListFragment, bundle)
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }
}