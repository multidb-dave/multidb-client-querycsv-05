package com.xql.clientmdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class FetchRecord {
    private String url;
    private String queryId;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String fetch() {
        String jsonStr = "";
        String requestBody = "";
        // String url = "http://localhost:8080/fetch";

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("queryId", this.queryId);
        try {
            JSONObject queryObj = new JSONObject(queryMap);
            jsonStr = queryObj.toString();
            requestBody = jsonStr;
        } catch (Exception ex) {
            System.out.println("ERROR: JSON Exception " + ex.getMessage());
            System.exit(0);
        }

        System.out.println("requestBody: " + requestBody);
        HttpClient client = HttpClient.newHttpClient();
        System.out.println("FetchRecord: this.url: " + this.url);
        // .uri(URI.create("https://postman-echo.com/get?param1=value1¶m2=value2"))
        String getUri = this.url + "?queryId=" + queryId;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getUri))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            jsonStr = response.body();
            System.out.println("responseBody: " + jsonStr);
        } catch (InterruptedException ex) {
            System.out.println("ERROR: HTTP InterruptedException " + ex.getMessage());
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("ERROR: HTTP IOException " + ex.getMessage());
            System.exit(0);
        }

        return jsonStr;
    }

}
