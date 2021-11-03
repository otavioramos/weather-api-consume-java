# OpenWeather API Consume with Java 11 - HttpClient

## How to run the project

- Inside the project's folder, build the application with the following command
```
mvn clean install -U
```

- After build, replace the the bold names below according the description and run the command:
<pre>
java -Dfile.encoding=UTF-8 -classpath <b>path_to_project_folder</b>/weather-api-consume-java/target/classes Program <b>cityName</b> <b>token_openweather</b>
</pre>
__path_to_project_folder__ = The whole path to where the project folder was cloned

__cityName__ = The name of the city you want to request the weather data. If the city name contains two words, seperate them by %20 instead of space.

__token_openweather__ = The token will should create in [https://openweathermap.org/current](https://openweathermap.org/current). You should register your account and create a token in this page: [API Keys](https://home.openweathermap.org/api_keys)

You should receive the response of the api in the console
