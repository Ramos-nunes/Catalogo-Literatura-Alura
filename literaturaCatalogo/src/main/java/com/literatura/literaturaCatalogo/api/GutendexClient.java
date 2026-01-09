package com.literatura.literaturaCatalogo.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexClient {

    private static final String URL_BASE = "https://gutendex.com/books/?search=";

    public String buscarLivro(String titulo) {

        try {
            String url = URL_BASE + titulo.replace(" ", "%20");

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar API: " + e.getMessage());
        }
    }
}
