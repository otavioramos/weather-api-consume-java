# OpenWeather API Consume with Java 11 - HttpClient

## How to run the project

Inside your terminal, type the command like this:
```
java -Dfile.encoding=UTF-8 -classpath path_to_project_folder/weather-api-consume-java/target/classes Program cityName token_openweather
```
path_to_project_folder = The whole path to where the project folder was cloned

cityName = The name of the city you want to request the weather data. If the city name contains two words, seperate them by %20 instead of space.

token_openweather = The token will should create in [https://openweathermap.org/current](https://openweathermap.org/current). You should register your account and create a token in this page: [API Keys](https://home.openweathermap.org/api_keys)