import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyAPI {
    private static final String API_BASE_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String apiUrl = API_BASE_URL + baseCurrency;
        StringBuilder response = new StringBuilder();

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            throw new IOException("Failed to retrieve data from API. Response code: " + responseCode);
        }

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        JsonObject rates = jsonObject.getAsJsonObject("rates");
        return rates.get(targetCurrency).getAsDouble();
    }
}