package at.ict4d.weather2

data class WeatherResult(
    val name: String,
    val description: String,
    val temperature: String,
    val humidity: String,
    val windSpeed: String
)