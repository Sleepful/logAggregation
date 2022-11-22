package com.armory.logsort;

public class HeapInterface {
    final public FileHandler fh;
    final public String line;
    public HeapInterface(FileHandler fh, String line){
    this.fh = fh;
    this.line = line;
    }
}
