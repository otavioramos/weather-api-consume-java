package service;

import entities.OpenWeatherHttpClient;
import entities.OpenWeatherRequest;

import java.io.IOException;
import java.net.http.HttpResponse;

public class OpenWeatherService {

    private OpenWeatherRequest openWeatherRequest;
    private OpenWeatherHttpClient openWeatherHttpClient;

    public OpenWeatherService() {
    }

    public String getWeather() throws IOException, InterruptedException {
        HttpResponse<String> response;
        try {
            response = openWeatherHttpClient.sendRequest(openWeatherRequest.getRequest());
            return response.body();
        } catch (IOException | InterruptedException e) {
            System.out.printf("The request to (%s) fails", OpenWeatherRequest.BASE_URL);
            System.out.printf("\nReason: %s", e.getMessage());
            throw e;
        }
    }

    public void setOpenWeatherRequest(OpenWeatherRequest openWeatherRequest) {
        this.openWeatherRequest = openWeatherRequest;
    }

    public void setOpenWeatherHttpClient(OpenWeatherHttpClient openWeatherHttpClient) {
        this.openWeatherHttpClient = openWeatherHttpClient;
    }
}
