import helper.TerminalInput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by aaron on 4/28/2018.
 */
public class WebServer implements Runnable {
    private int port;
    private String rootDir ;

    public WebServer(){
        this(8080);
    }
    public WebServer(int port)
    {
        this(port,"Static");
    }

    public WebServer(int port, String rootDirectory)
    {
        this.port = port;
        this.rootDir = rootDirectory;
    }

    public void run() {
        Socket local_socket = null;
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            ApiAssigner apiAssigner = new ApiAssigner();
            TerminalInput terminalInput = new TerminalInput();
            new Thread(terminalInput,"console ready").start();

            while( terminalInput.isAlive() ) {
                try {
                    local_socket =  serverSocket.accept();
                    new Thread(new ConnectionHandler(local_socket, this.rootDir, apiAssigner), "client").start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}