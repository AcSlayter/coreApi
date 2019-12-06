package guildwars;

import com.config.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;


public class GetGuildWarsAPI {
    private String etfApiEndpoint;
    private String endpoint;

    public GetGuildWarsAPI(String endpoint) {
        this.endpoint = endpoint;
        this.etfApiEndpoint = getEndpoint();
    }

    private String getEndpoint() {
        return "http://guildwars2.ac-local.com:2845/";
    }

    public String getPublicJson(String itemName){
        try {
            URL url = new URL(etfApiEndpoint.concat(this.endpoint).concat(itemName));
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
            URL url = new URL(etfApiEndpoint.concat(this.endpoint));
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
