package com.brad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystemNotFoundException;

class WriterThread implements Runnable {

    private final String name;
    private final ApplicationContext applicationContext;
    private final DedupeStrategy dedupeStrategy = new DedupeStrategy();
    private final Thread t;
    private PrintWriter writer;

    WriterThread(String threadname, ApplicationContext ctx) {
        name = threadname;
        this.applicationContext = ctx;

        t = new Thread(this, name);
        applicationContext.registerThread(t);
        t.start();
    }

    private void deleteLogFile(){
        File file = new File("numbers.log");

        file.delete();
    }

    private void openFile() throws UnsupportedEncodingException, FileNotFoundException {
        writer = new PrintWriter("numbers.log", "UTF-8");
    }

    private void writeFile(String number){
        writer.println(number);
    }

    public void run() {

        try {
            deleteLogFile();
            openFile();
        }
        catch( FileNotFoundException e ){
            throw new FileSystemNotFoundException();
        }
        catch( UnsupportedEncodingException e ){
            throw new FileSystemNotFoundException();
        }

        while (! Thread.currentThread().isInterrupted() ) {
            String number = applicationContext.queue.poll();
            if (number != null) {
                if (dedupeStrategy.isDuplicate(number)) {
                    applicationContext.trafficReport.incrementDuplicate();
                } else {
                    applicationContext.trafficReport.incrementRecieved();
                    writeFile((number));
                    dedupeStrategy.addKey(number);
                }
            }
        }
    }
}


