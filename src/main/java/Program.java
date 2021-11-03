import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;

public class Program {

    static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

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

        HttpClient httpClient = setupHttpClient();

        String uri = setupAndFormatUri(cityName, token);

        HttpRequest request = setupHttpRequest(uri);

        Optional<HttpResponse<String>> response = Optional.empty();

        try {
            response = Optional.ofNullable(sendRequest(httpClient, request));
        } catch (IOException | InterruptedException e) {
            System.out.printf("The request to (%s) fails", BASE_URL);
            System.out.printf("\nReason: %s", e.getMessage());
        }

        response.ifPresent((resp) -> {
            System.out.printf("Status code: %d",resp.statusCode());
            System.out.printf("\nBody: %s",resp.body());
        });

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

    private static HttpResponse<String> sendRequest(HttpClient httpClient, HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
    }

    /**
     * @param uri An uri on a string format
     */
    private static HttpRequest setupHttpRequest(String uri) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uri))
                .build();
    }

    private static HttpClient setupHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    /**
     * @param cityName The name of the city that the user wants to know the weather
     * @param token A token created on https://home.openweathermap.org/api_keys for make a request
     */
    private static String setupAndFormatUri(String cityName, String token) {
        return String.format("%s?q=%s&appid=%s&units=metric",BASE_URL,cityName, token);
    }
}

