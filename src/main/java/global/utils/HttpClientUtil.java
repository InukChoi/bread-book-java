package global.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientUtil {
    private static HttpClient client;
    private static HttpRequest httpRequest;
    private static HttpResponse<String> httpResponse;

    public static int getTotalAmount(String secret, String paymentId) {
        try {
            client = HttpClient.newHttpClient();
            httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.portone.io/payments/" + paymentId))
                    .header("Authorization", "PortOne " + secret)
                    .GET()
                    .build();
            httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = httpResponse.body();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode amountNode = rootNode.get("amount");
            int total = amountNode.get("total").asInt();

            return total;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sendPost(String url, String contentType, String body) {
        try {
            client = HttpClient.newHttpClient();
            httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", contentType)
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return httpResponse.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAccessToken(String code) {
        String response = sendPost(
                "https://kauth.kakao.com/oauth/token",
                "application/x-www-form-urlencoded;charset=utf-8",
                "grant_type=authorization_code&client_id=9e1c7bea5230c788e1ca6a34a43ec68d&redirect_uri=http://localhost:8080/kakao/code&code=" + code
        );

        return response.split("\"access_token\":\"")[1].split("\"")[0];
    }

    public static String getUserNickname(String code) {
        try {
            String token = getAccessToken(code);
            httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://kapi.kakao.com/v2/user/me"))
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                    .header("Authorization", "Bearer " + token)
                    .GET()
                    .build();

            httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body().split("\"nickname\":\"")[1].split("\"")[0];
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
