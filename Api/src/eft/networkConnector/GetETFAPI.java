package eft.networkConnector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetETFAPI {
    private String MYURL = "http://jenkins.ac-local.com:2846";
    private String endpoint;

    public GetETFAPI(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getPublicJson(String itemName){
        try {
            URL url = new URL("http://jenkins.ac-local.com:2846".concat(this.endpoint).concat(itemName));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuilder stringBuilder = new StringBuilder();
            while ((output = br.readLine()) != null) {
                stringBuilder.append(output);
            }

            conn.disconnect();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "test";
    }

    public String getPublicJson(){
        try {
            URL url = new URL("http://jenkins.ac-local.com:2846".concat(this.endpoint));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuilder stringBuilder = new StringBuilder();
            while ((output = br.readLine()) != null) {
                stringBuilder.append(output);
            }

            conn.disconnect();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "test";
    }
}
