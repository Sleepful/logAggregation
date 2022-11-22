package com.armory.logsort;

import com.armory.logsort.generator.LogGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LogSortApplication {

    public static void main(String[] args) {
        // generate log files
        // commented to test without generating the files
        new LogGenerator(1000, 1000).generate("./src/main/resources/generated/logs/");
        // these params generate around 47 MB of files

        // read log files
        final List<File> logFiles = Arrays.asList(new File("./src/main/resources/generated/logs/").listFiles());

        final Instant start = Instant.now();

        final MaxHeap heap = new MaxHeap();

        for (File file : logFiles){
                final FileHandler fh = new FileHandler(file);
                heap.add(fh);
        }
        final Object[] arr = heap.prq.toArray();
        System.out.println("arr " + arr.length);
        try {
                FileWriter fw = new FileWriter("final.log");
                fw.flush(); //empty it
        String line = null;
        do {
                line = heap.pop();
                if(line == null) continue;
                try {
                        fw.write(line + "\n");
                } catch (Exception e) {
                        e.printStackTrace();
                        return;
                }
                // persist date
                // print out or file
        } while(line != null);
        fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // new LogPrinter().printLogs(logFiles);

        final Instant finish = Instant.now();
        System.out.println("\nLog sorting and printing took: " + (finish.toEpochMilli() - start.toEpochMilli()) + "ms");
    }

}
