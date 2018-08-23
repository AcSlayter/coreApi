/**
 * Created by aaron on 5/26/2018.
 */

public class ServerStart {
    public static void main(String[] args) {
        int port = 8080;

        if ( args.length == 1 ){
            port = Integer.parseInt(args[0]);
        }

        Thread webServerTHREAD = new Thread(new WebServer(port,"Static"), "Thread 1");
        Thread webServerMonitorTHREAD = new Thread(new WebServer(2020,"logs"), "Thread 1");
        webServerMonitorTHREAD.start();
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
