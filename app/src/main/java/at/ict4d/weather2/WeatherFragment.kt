package at.ict4d.weather2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import at.ict4d.weather2.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: FragmentWeatherBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        binding.clickListener = this

        viewModel.searchResult.observe(viewLifecycleOwner, Observer { weatherResult ->
            binding.headline.text = getString(R.string.weather_headline, weatherResult?.name ?: "")
            binding.weatherResult = weatherResult
        })

        return binding.root
    }

    fun onSearchClicked() {
        val searchText = binding.searchEditText.text.toString()

        Toast.makeText(activity, searchText, Toast.LENGTH_LONG).show()

        binding.weatherResult = null
        if (searchText.isNotEmpty()) {
            viewModel.searchForWeather(searchText)
        }
    }

    fun onOpenMap() {
        val gmmIntentUri = Uri.parse("geo:0,0?q=${binding.weatherResult?.name}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}
