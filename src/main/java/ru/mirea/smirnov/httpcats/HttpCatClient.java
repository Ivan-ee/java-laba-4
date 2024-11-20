package ru.mirea.smirnov.httpcats;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class HttpCatClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        File dir = new File("cats");

        if (!dir.exists()) {
            dir.mkdir();
        }

        HttpClient client = HttpClient.newHttpClient();

        for (int i = 0; i < 600; i++) {

            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create("https://http.cat/" + i))
                    .GET()
                    .build();

            HttpResponse<byte[]> response = client.send(request,
                    HttpResponse.BodyHandlers.ofByteArray());

            System.out.println(response.toString());
        }
    }
}
