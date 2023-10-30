package com.xql.clientmdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ListSchemas {
    private String url;

    public ListSchemas() {
    }

    public ListSchemas(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSchemas() {
        try {
            // System.out.println("requestBody: " + requestBody);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.url))
                    .GET()
                    .build();
            System.out.println("request: " + request.toString());

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            String jsonStr = "";
            jsonStr = response.body();
            System.out.println(jsonStr);
        } catch (InterruptedException ex) {
            System.out.println("ERROR: HTTP InterruptedException " + ex.getMessage());
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("ERROR: HTTP IOException " + ex.getMessage());
            System.exit(0);
        }

        return "";
    }
}
