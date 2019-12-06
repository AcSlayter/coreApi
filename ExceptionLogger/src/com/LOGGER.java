package com;

import com.FileSystemFile;

/**
 * Created by aaron on 5/28/2018.
 */
public class LOGGER {
    private String file_location = "default.txt";
    private boolean printToFile = false;
    private boolean printToConsole = false;

    public LOGGER() {
        printToFile = false;
        printToConsole = true;
    }

    public LOGGER ( String filePath ) {
        this(filePath, false);
    }

    public LOGGER (String filePath , Boolean isConsole){
        this.file_location = filePath;
        this.printToFile = true;
        this.printToConsole = isConsole;
    }

    public void log( Exception e, String aditional_info) {
        StringBuilder log = new StringBuilder();
        log = log.append("ErrorOccurred=").append(e.toString()).append(", ").append("startingPoint=").append(e.getStackTrace()[0].toString());
        if(aditional_info != null){
            log = log.append(", ").append("additionalInfo=").append(aditional_info);
        }

        if(printToConsole) {
            System.out.println(log.toString());
        }
        if(printToFile){
            FileSystemFile.writeLogFile(file_location,log.toString());
        }
    }

    public void log( Exception e ) {
        log(e,null);
    }

    public void log(String concat) {
        StringBuilder log = new StringBuilder();
        log = log.append("success=true").append(", ");
        if(concat != null){
            log = log.append("additionalInfo=").append(concat);
        }

        if(printToConsole) {
            System.out.println(log.toString());
        }
        if(printToFile){
            FileSystemFile.writeLogFile(file_location,log.toString());
        }
    }
}
