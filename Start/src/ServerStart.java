import com.WebServer;
import com.config.Config;

import java.util.Properties;

/**
 * Created by aaron on 5/26/2018.
 */

public class ServerStart {

    public static void main(String[] args) {
        Properties config = new Properties();

        try {
            config = Config.getProperties();
        } catch (Exception e) {
            config.putAll(Config.getDefaults());
            Config.SaveData(config);
        }

        int main_port = Integer.parseInt(config.getProperty(Config.MAIN_PORT));
        int error_port = Integer.parseInt(config.getProperty(Config.ERROR_PORT));
        Boolean is_Debug = Boolean.parseBoolean(config.getProperty(Config.IS_DEBUG));



        Thread webServerTHREAD = new Thread(new WebServer(main_port,"Static", new ApiAssigner()), "Thread 1");
        Thread webServerMonitorTHREAD = new Thread(new WebServer(error_port,"logs"), "Thread 1");


        if(!is_Debug){
            webServerMonitorTHREAD.start();
        }
        webServerTHREAD.start();

        while(webServerTHREAD.isAlive() || webServerMonitorTHREAD.isAlive()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
