package repositories;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;


public class HttpConnect {


    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    private BigDecimal rate;

    public String getRate(Boolean inversely) {
        return (inversely ? rate : BigDecimal.ONE.divide(rate, 8, RoundingMode.UP)).toString();
    }

    public void sendGet() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://api.nbp.pl/api/exchangerates/rates/a/gbp/?format=json"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
        HttpResponse<String> response = null;
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        rate = new BigDecimal(prettify(response.body()));


    }

    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        JsonArray rates = (JsonArray) json.get("rates");
        Iterator ratesItr = rates.iterator();
        JsonObject ratesElement = null;
        while (ratesItr.hasNext()) {
            ratesElement = (JsonObject) ratesItr.next();
        }
        assert ratesElement != null;
        return ratesElement.get("mid").toString();
    }


}