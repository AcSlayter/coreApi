package eft.networkConnector;

import com.config.Config;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;


public class GetETFAPI {
    private String etfApiEndpoint;
    private String endpoint;

    public GetETFAPI(String endpoint) {
        this.endpoint = endpoint;
        this.etfApiEndpoint = getEndpoint();
    }

    private String getEndpoint() {
        Properties config = new Properties();
        try {
            config = Config.getProperties();
        } catch (Exception e) {
            config.putAll(Config.getDefaults());
            Config.SaveData(config);
        }
        try {
            String host = config.getProperty(Config.ETFAPIHOST);
            String port = config.getProperty(Config.ETFAPIPORT);
            if(host != null || port != null) {
                return host + ":" + port;
            }
        } catch (Exception e) {

        }

        return "http://jenkins.ac-local.com:2846";
        //

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
