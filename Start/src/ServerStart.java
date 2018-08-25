import java.util.Properties;

/**
 * Created by aaron on 5/26/2018.
 */

public class ServerStart {
    public static final String MAIN_PORT = "MPORT";
    public static final String ERROR_PORT = "EPORT";
    public static final String IS_DEBUG = "DEBUG";


    public static void main(String[] args) {
        Properties config = new Properties();

        try {
            config = Config.getProperties();
        } catch (Exception e) {
            config.setProperty(ServerStart.MAIN_PORT, "8080");
            config.setProperty(ServerStart.ERROR_PORT, "2020");
            config.setProperty(ServerStart.IS_DEBUG, "true");
            Config.SaveData(config);
        }

        int main_port = Integer.parseInt(config.getProperty(MAIN_PORT));
        int error_port = Integer.parseInt(config.getProperty(ERROR_PORT));
        Boolean is_Debug = Boolean.parseBoolean(config.getProperty(IS_DEBUG));



        Thread webServerTHREAD = new Thread(new WebServer(main_port,"Static"), "Thread 1");
        Thread webServerMonitorTHREAD = new Thread(new WebServer(error_port,"logs"), "Thread 1");

        webServerTHREAD.start();
        if(!is_Debug){
            webServerMonitorTHREAD.start();
        }

        while(webServerTHREAD.isAlive() || webServerMonitorTHREAD.isAlive()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
