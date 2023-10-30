package com.xql.clientmdb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Husni Fahmi
// husnifahmi@outlook.com
// pgseliso@gmail.com
// 2023-06-30

public class App {

    public static void main(String[] args) {
        String queryStr = "";

        if (args.length != 1) {
            System.out.println("Usage: program <queryfile>");
            System.out.println("Error: queryfile argument was not found.");
            System.exit(1);
        }

        for (String filename : args) {
            System.out.println(filename);
            try {
                queryStr = new String(
                        Files.readAllBytes(Paths.get(filename)));
                System.out.println("queryStr: \n" + queryStr);
            } catch (IOException ex) {
                System.out.println("IOException: " + ex.getMessage());
            }
        }

        // String baseUrl = "http://localhost:8080";
        // String baseUrl = "http://api001.multidb.net:8080";
        // String baseUrl = "https://api001.multidb.net:8443";
        String baseUrl = "https://api001.multidb.net:8443";
        String url = baseUrl + "/api/v1/schemas";

        // ListSchemas listSchemas = new ListSchemas();
        // listSchemas.setUrl(url);
        // listSchemas.getSchemas();

        QueryCsv queryCsv = new QueryCsv();
        // url = baseUrl + "/auth/login";
        // createFetchQuery.setUrl(url);

        queryCsv.setEmail("multidbuser@onesql.com");
        queryCsv.setPassword("Mdb2023xyzbcd");

        // url = baseUrl + "/api/v1/createfetchq";
        url = baseUrl + "/api/v1/querycsv";
        queryCsv.setBaseUrl(baseUrl);
        queryCsv.setUrl(url);

        // createFetchQuery.setEmail("multidbuser@onesql.com");
        // createFetchQuery.setPassword("Mdb2023xyzbcd");

        queryCsv.setQueryStr(queryStr);
        String jsonStr = "";
        jsonStr = queryCsv.sendQuery();

        // https://www.baeldung.com/java-download-file
        // https://www.baeldung.com/java-download-file#using-nio

        // https://www.geeksengine.com/database/problem-solving/northwind-queries-part-1.php
        // MySQL Northwind Queries - Part 1

        String queryId = "";
        String filePath = "";
        try {
            JSONParser jparser = new JSONParser();
            JSONObject jobj = (JSONObject) jparser.parse(jsonStr);
            if (jobj == null) {
                System.out.println("ERROR: JSONParser returns NULL.");
                System.exit(2);
            }
            String successStr = "";
            String dataStr = "";
            Object successObj = jobj.get("success");
            if (successObj != null) {
                successStr = successObj.toString();
            } else {
                // System.out.println("success is NULL.");
                Object errorObj = jobj.get("errorCode");
                if (errorObj != null) {
                    String errorStr = errorObj.toString();
                    System.out.println("Error: " + errorStr);
                }
                System.exit(0);
            }
            System.out.println("success: " + successStr);

            if (successStr.equalsIgnoreCase("true")) {
                // get data
                Object dataObj = jobj.get("data");
                if (dataObj != null) {
                    dataStr = dataObj.toString();
                    JSONObject qresultObj = (JSONObject) jparser.parse(dataStr);
                    Object qidObj = qresultObj.get("queryId");
                    if (qidObj != null) {
                        String qidStr = qidObj.toString();
                        System.out.println("queryId: " + qidStr);
                        queryId = qidStr;
                    }

                    Object fileObj = qresultObj.get("filePath");
                    if (qidObj != null) {
                        String fileStr = fileObj.toString();
                        System.out.println("filePath: " + fileStr);
                        filePath = fileStr;
                    }
                } else {
                    System.out.println("value is NULL.");
                    System.exit(0);
                }
            } else {
                System.out.println("ERROR: ");
                System.exit(0);
            }
        } catch (ParseException ex) {
            System.out.println("ERROR: ParseException: " + ex.getMessage());
        }

        String fileUrl = baseUrl + filePath;
        String saveDir = ".";

        try {
            queryCsv.downloadFile(fileUrl, saveDir);
        } catch (IOException ex) {
            System.out.println("downloadFile IOException: " + ex.getMessage());
        }

        {
            System.exit(0);
        }

        ExecQuery execQuery = new ExecQuery();
        url = baseUrl + "/api/v1/execq";
        // url = "http://api001.multidb.net:8080/api/v1/execq";
        execQuery.setUrl(url);
        execQuery.setQueryId(queryId);
        jsonStr = execQuery.execute();
        System.out.println("execQuery() returns ");
        System.out.println(jsonStr);
        System.out.println();

        try {
            JSONParser jparser = new JSONParser();
            JSONObject jobj = (JSONObject) jparser.parse(jsonStr);
            if (jobj == null) {
                System.out.println("ERROR: JSONParser returns NULL.");
                System.exit(0);
            }
            String successStr = "";
            String dataStr = "";
            Object successObj = jobj.get("success");
            if (successObj != null) {
                successStr = successObj.toString();
            } else {
                System.out.println("success is NULL.");
                System.exit(0);
            }
            System.out.println("success: " + successStr);

            if (successStr.equalsIgnoreCase("true")) {
                // get data
                Object dataObj = jobj.get("data");
                if (dataObj != null) {
                    dataStr = dataObj.toString();
                    System.out.println("data: " + dataStr);
                    // JSONObject qresultObj = (JSONObject) jparser.parse(dataStr);
                    // Object qidObj = qresultObj.get("queryId");
                    // if (qidObj != null) {
                    // String qidStr = qidObj.toString();
                    // System.out.println("queryId: " + qidStr);
                    // queryId = qidStr;
                    // }
                } else {
                    System.out.println("Value of data is NULL.");
                    System.exit(0);
                }
            } else {
                System.out.println("ERROR: ");
                System.exit(0);
            }
        } catch (ParseException ex) {
            System.out.println("ERROR: ParseException: " + ex.getMessage());
        }

        int i = 0;
        while (true) {
            if (i > 1000) {
                // limited to 1000 rows
                break;
            }
            i++;
            FetchRecord fetchRecord = new FetchRecord();
            url = baseUrl + "/api/v1/fetch";
            // url = "http://api001.multidb.net:8080/api/v1/fetch";
            fetchRecord.setUrl(url);
            // queryId = queryId + ";]";
            fetchRecord.setQueryId(queryId);
            jsonStr = fetchRecord.fetch();

            try {
                JSONParser jparser = new JSONParser();
                JSONObject jobj = (JSONObject) jparser.parse(jsonStr);
                if (jobj == null) {
                    System.out.println("ERROR: JSONParser returns NULL.");
                    System.exit(0);
                }
                String successStr = "";
                String dataStr = "";
                Object successObj = jobj.get("success");
                if (successObj != null) {
                    successStr = successObj.toString();
                } else {
                    System.out.println("success is NULL.");
                    System.exit(0);
                }
                System.out.println("success: " + successStr);

                if (successStr.equalsIgnoreCase("true")) {
                    // get data
                    Object dataObj = jobj.get("data");
                    if (dataObj != null) {
                        dataStr = dataObj.toString();
                        System.out.println("data: " + dataStr);
                        JSONObject fetchObj = (JSONObject) jparser.parse(dataStr);
                        Object columnObj = fetchObj.get("column");
                        if (columnObj != null) {
                            String columnStr = columnObj.toString();
                            System.out.println("column: " + columnStr);
                            if (columnStr.length() == 0) {
                                System.out.println("break: columnStr is empty.");
                                break;
                            }
                            // queryId = qidStr;
                        } else {
                            System.out.println("break: columnObj is null");
                            break;
                        }

                        Object rowObj = fetchObj.get("row");
                        if (rowObj != null) {
                            String rowStr = rowObj.toString();
                            System.out.println("row: " + rowStr);
                            if (rowStr.length() == 0) {
                                System.out.println("break: rowStr is empty.");
                                break;
                            }
                            // queryId = qidStr;
                        } else {
                            System.out.println("break: rowObj is null");
                            break;
                        }
                    } else {
                        System.out.println("Value of data is NULL.");
                        // System.exit(0);
                        System.out.println("break: dataObj is null");
                        break;
                    }
                } else {
                    String messageStr = "";
                    Object messageObj = jobj.get("message");
                    if (messageObj != null) {
                        messageStr = messageObj.toString();
                        System.out.println("message: " + messageStr);
                    } else {
                        System.out.println("message object is NULL.");
                        System.exit(0);
                    }
                    break;
                }
            } catch (ParseException ex) {
                System.out.println("ERROR: ParseException: " + ex.getMessage());
                break;
            }

        }

        // {
        // System.exit(0);
        // }

        CloseQuery closeQuery = new CloseQuery();
        url = baseUrl + "/api/v1/closeq";
        // url = "http://api001.multidb.net:8080/api/v1/closeq";
        closeQuery.setUrl(url);
        // queryId = queryId + ".x";
        closeQuery.setQueryId(queryId);
        jsonStr = closeQuery.close();
        System.out.println("jsonStr: " + jsonStr);

        try {
            JSONParser jparser = new JSONParser();
            JSONObject jobj = (JSONObject) jparser.parse(jsonStr);
            if (jobj == null) {
                System.out.println("ERROR: JSONParser returns NULL.");
                System.exit(0);
            }
            String successStr = "";
            String messageStr = "";

            Object successObj = jobj.get("success");
            if (successObj != null) {
                successStr = successObj.toString();
            } else {
                System.out.println("success is NULL.");
                System.exit(0);
            }
            System.out.println("success: " + successStr);

            Object messageObj = jobj.get("message");
            if (messageObj != null) {
                messageStr = messageObj.toString();
            } else {
                System.out.println("message is NULL.");
                System.exit(0);
            }
            System.out.println("message: " + messageStr);
        } catch (ParseException ex) {
            System.out.println("ERROR: JSON ParseException: " + ex.getMessage());
        }
    }
}
