package com.xql.clientmdb;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CreateFetchQuery {
    private String baseUrl;
    private String url;
    private String email;
    private String password;
    private String accessToken;
    private String queryStr;
    private int limit;

    public CreateFetchQuery() {
        this.limit = 100;
    }

    /**
     * @return String return the baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @param baseUrl the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getQueryStr() {
        return this.queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    /**
     * @return int return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return String return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String create() {
        System.out.println("create()");
        try {
            loginJson();
        } catch (InterruptedException ex) {
            System.out.println("ERROR InterruptedException: " + ex.getMessage());
            return "";
        } catch (IOException ex) {
            System.out.println("ERROR IOException: " + ex.getMessage());
            return "";
        }

        String jsonStr = "";
        String requestBody = "";
        try {
            HashMap<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("query", this.queryStr);
            queryMap.put("limit", Integer.toString(limit));

            jsonStr = "";
            JSONObject queryObj = new JSONObject(queryMap);
            jsonStr = queryObj.toString();
            requestBody = jsonStr;
            System.out.println("requestBody: " + requestBody);
            System.out.println("accessToken: " + accessToken);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.url))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .build();
            System.out.println("accessToken: " + accessToken);
            System.out.println("request: " + request.toString());
            System.out.println("header: " + request.headers().toString());
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            jsonStr = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            JsonNode jsonNode = objectMapper.readTree(jsonStr);
            String prettyJson = objectMapper.writeValueAsString(jsonNode);
            System.out.println(prettyJson);
        } catch (InterruptedException ex) {
            System.out.println("ERROR: HTTP InterruptedException " + ex.getMessage());
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("ERROR: HTTP IOException " + ex.getMessage());
            System.exit(0);
        }

        return jsonStr;
    }

    public void login() throws IOException, InterruptedException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI("http://localhost:8080/auth/login"))
                    .addParameter("email", "multidbuser@onesql.com")
                    .addParameter("password", "Mdb2023xyzbcd")
                    .build();

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                String jsonStr = EntityUtils.toString(response.getEntity());

                System.out.println(jsonStr);
                try {
                    JSONObject jobj = (JSONObject) new JSONParser().parse(jsonStr);
                    Object tokenObj = jobj.get("accessToken");
                    if (tokenObj != null) {
                        this.accessToken = tokenObj.toString();
                    } else {
                        this.accessToken = "";
                    }
                    System.out.println("accessToken: " + accessToken);
                } catch (org.json.simple.parser.ParseException ex) {
                    System.out.println("ParseException: " + ex.getMessage());
                }
            } catch (IOException ex) {
                System.out.println("ParseException: " + ex.getMessage());
            } finally {
                response.close();
            }
        } catch (ClientProtocolException ex) {
            System.out.println("ClientProtocolException: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.out.println("URISyntaxException: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } finally {
            httpclient.close();
        }
    }

    public String loginJson() throws IOException, InterruptedException {
        System.out.println("loginJson()");
        String jsonStr = "";
        String requestBody = "";
        String loginUrl = this.baseUrl + "/auth/login";

        try {
            Map<String, String> loginMap = new HashMap<String, String>();
            loginMap.put("email", "multidbuser@onesql.com");
            loginMap.put("password", "Mdb2023xyzbcd");

            jsonStr = "";
            JSONObject loginObj = new JSONObject(loginMap);
            jsonStr = loginObj.toString();
            requestBody = jsonStr;
            System.out.println("loginUrl: " + loginUrl);
            System.out.println("requestBody: " + requestBody);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(loginUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();
            System.out.println("request: " + request.toString());

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            jsonStr = response.body();
            // System.out.println("responseBody: " + jsonStr);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            JsonNode jsonNode = objectMapper.readTree(jsonStr);
            String prettyJson = objectMapper.writeValueAsString(jsonNode);
            System.out.println(prettyJson);
            JsonNode jsonData = jsonNode.get("data");
            if (jsonData != null) {
                System.out.println("jsonData: " + jsonData.toString());
                JsonNode jsonToken = jsonData.get("accessToken");
                if (jsonToken != null) {
                    this.accessToken = jsonToken.asText();
                    System.out.println("accessToken: " + accessToken);
                }
            }
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
