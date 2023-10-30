package com.xql.clientmdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CreateQuery {
    private String url;
    private String queryStr;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQueryStr() {
        return this.queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    CreateQuery() {
    }

    public String createFetch() {
        String requestBody = "";

        HashMap<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("query", this.queryStr);

        String jsonStr = "";
        JSONObject queryObj = new JSONObject(queryMap);
        jsonStr = queryObj.toString();
        requestBody = jsonStr;
        try {
            // System.out.println("requestBody: " + requestBody);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.url))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            System.out.println("request: " + request.toString());

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            jsonStr = response.body();
            System.out.println(jsonStr);
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
