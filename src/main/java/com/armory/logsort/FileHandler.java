package com.armory.logsort;
import java.io.*;


public class FileHandler {
    private FileReader fReader ;
    private BufferedReader bReader ;
    public File file ;

    public FileHandler(File file){
        try {
            final FileReader fReader = new FileReader(file);
            final BufferedReader bReader = new BufferedReader(fReader);
            this.fReader = fReader;
            this.bReader = bReader;
            this.file = file;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readLine(){
        try {
            final String line = this.bReader.readLine();
            return line;// possible null
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readDate(){ // unused
        final String line = this.readLine();
        if(line == null) return null;
        final String date = line.substring(0, line.indexOf(","));
        return date;
    }
}
