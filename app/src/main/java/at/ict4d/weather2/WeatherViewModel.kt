package at.ict4d.weather2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

    private val weatherResultList = listOf(
        WeatherResult("Beira", "clear sky", "10C", "70%", "10km/h"),
        WeatherResult("Paris", "super hot", "40C", "40%", "20km/h"),
        WeatherResult("Vienna", "smooth", "30C", "50%", "40km/h"),
        WeatherResult("Bangalore", "raining", "45C", "20%", "70km/h")
    )

    val searchResult = MutableLiveData<WeatherResult?>()

    fun searchForWeather(query: String) {
        // make here an asynchronous call to an API to get real weather data
        val result =
            weatherResultList.find { weatherResult -> weatherResult.name.toLowerCase().contains(query.toLowerCase()) }
        searchResult.value = result
    }
}
