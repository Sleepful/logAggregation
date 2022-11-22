package com.armory.logsort;
import java.io.*;
import java.util.PriorityQueue;

// I guess this is actually a MinHeap, but lets leave
// the naming like this for now...
public class MaxHeap {
    public PriorityQueue<HeapInterface> prq;

    public MaxHeap(){
        // ISO date is lexicographically comparable
        final PriorityQueue<HeapInterface> prq = new PriorityQueue <> ((a, b)->{
                final int r = a.line.compareTo(b.line);
                if(r>0) return 1;
                if(r<0) return -1;
                return 0;
        });
        this.prq = prq;
    }

    public void add(FileHandler fh){
        final String line = fh.readLine();
        if(line == null) {
            // indicates end of file
            return;
        };
        final HeapInterface tuple = new HeapInterface(fh, line);
        prq.offer(tuple);
        return;
    }

    public String pop(){
        final HeapInterface res = prq.poll();
        if(res == null) {
            // indicates end of heap
            return null;
        }
        final FileHandler fh = res.fh;
        final String line = res.line;
        this.add(fh);// add next line
        return line;
    }

}
