package entities;

import java.net.URI;
import java.net.http.HttpRequest;

public class OpenWeatherRequest {

    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final String cityName;
    private final String apiToken;

    private URI uri;

    private HttpRequest request;

    public OpenWeatherRequest(String cityName, String apiToken) {
        this.cityName = cityName;
        this.apiToken = apiToken;
        setupUri();
        setupHttpRequest();
    }

    private void setupHttpRequest() {
        request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
    }

    private void setupUri() {
        uri = URI.create(formatUri());
    }

    private String formatUri() {
        return String.format("%s?q=%s&appid=%s&units=metric",BASE_URL,cityName, apiToken);
    }

    public HttpRequest getRequest() {
        return request;
    }
}
