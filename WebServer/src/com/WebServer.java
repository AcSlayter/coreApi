package com;

import com.helper.TerminalInput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by aaron on 4/28/2018.
 */
public class WebServer implements Runnable {
    private int port;
    private String rootDir ;
    ApiHandler apiHandler;

    public WebServer(){
        this(8080);
    }
    public WebServer(int port)
    {
        this(port,"Static");
    }

    public WebServer(int port, String rootDirectory)
    {
        this(port,rootDirectory, new ApiHandler());
    }

    public WebServer(int port, String rootDirectory, ApiHandler apiHandler){
        this.port = port;
        this.rootDir = rootDirectory;
        this.apiHandler = apiHandler;
    }

    public void run() {
        Socket local_socket = null;
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            TerminalInput terminalInput = new TerminalInput();
            new Thread(terminalInput,"console ready").start();

            while( terminalInput.isAlive() ) {
                try {
                    local_socket =  serverSocket.accept();
                    new Thread(new ConnectionHandler(local_socket, this.rootDir, this.apiHandler), "client").start();
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