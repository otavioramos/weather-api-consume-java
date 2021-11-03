import entities.OpenWeatherHttpClient;
import entities.OpenWeatherRequest;
import service.OpenWeatherService;

import java.io.IOException;

public class Program {

    public static void main(String[] args) {
        final String token;
        final String cityName;

        try {
            cityName = getArgument(args, 0);
            token = getArgument(args, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("\nAn error occurred when the arguments was read");
            return;
        }

        OpenWeatherRequest openWeatherRequest = new OpenWeatherRequest(cityName, token);
        OpenWeatherHttpClient openWeatherHttpClient = new OpenWeatherHttpClient(20);

        OpenWeatherService service = new OpenWeatherService();
        service.setOpenWeatherHttpClient(openWeatherHttpClient);
        service.setOpenWeatherRequest(openWeatherRequest);

        try {
            System.out.printf("\nResponse: %s",service.getWeather());
        } catch (IOException | InterruptedException e) {
            System.exit(0);
        }
    }

    /**
     * @param arguments An array of arguments passed by user for main method
     * @param position A position of argument in array that should return the value expected
     */
    private static String getArgument(String[] arguments, int position) throws IllegalArgumentException{
        try {
            return arguments[position];
        } catch (Exception e) {
            System.out.println("The program fails in catch the arguments passed");
            System.out.printf("Error: %s", e.getMessage());
            throw new IllegalArgumentException("The arguments passed are invalid");
        }
    }
}

