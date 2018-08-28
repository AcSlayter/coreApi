package com.helper;

import java.util.Scanner;

/**
 * Created by aaron on 5/24/2018.
 */
public class TerminalInput implements Runnable{
    boolean alive = true;

    public boolean isAlive(){
        if(alive) {
            return true;
        }

        return false;
    }

    public void run() {
        while ( this.alive ){
            Scanner scanner = new Scanner(System.in);
            String consoleInput = scanner.nextLine();
            if( consoleInput.equals("kill") ){
                this.alive = false;
                System.out.println("Command complete : " + consoleInput);
            } else {
                System.out.println("invalid command : " + consoleInput);
            }
        }

    }
}
