package service;

import com.google.gson.Gson;
import entities.OpenWeatherHttpClient;
import entities.OpenWeatherRequest;
import entities.UnauthorizedResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OpenWeatherServiceTest {

    OpenWeatherService openWeatherService;
    static final Gson GSON = new Gson();

    @Before
    public void setOpenWeatherService(){
        openWeatherService = new OpenWeatherService();
    }

    @Test
    public void shouldReturnUnauthorizedForInvalidToken() throws IOException, InterruptedException {
        openWeatherService.setOpenWeatherRequest(new OpenWeatherRequest("London","invalid-token"));
        openWeatherService.setOpenWeatherHttpClient(new OpenWeatherHttpClient(20));

        UnauthorizedResponse response = GSON.fromJson(openWeatherService.getWeather(), UnauthorizedResponse.class);

        assertThat(response.getCod(), is("401"));
        assertThat(response.getMessage(), containsString("Invalid API key"));
    }
}