package com.brad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class ListenerThread implements Runnable {

    private String name;
    private final Thread t;
    private final ApplicationContext applicationContext;
    private final Pattern pattern;

    ListenerThread( String threadname, ApplicationContext ctx){
        name = threadname;
        this.applicationContext = ctx;

        pattern = Pattern.compile("[0-9]{9,9}");
        t = new Thread(this, name);
        ctx.registerThread(t);
        t.start();
    }

    public void run() {

        try {
            while( true ) {
                Socket clientSock = applicationContext.socket.accept();
                String inputLine;
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

                // read from socket
                while ((inputLine = in.readLine()) != null ) {
                    if( inputLine.equals("terminate") ){ // check for magic string
                        applicationContext.terminate();
                    }
                    else {
                        Matcher m = pattern.matcher(inputLine);
                        if (inputLine.length() == 9 && m.find()  && ! Thread.currentThread().isInterrupted() ) { //properly formed message
                            applicationContext.queue.add(inputLine);
                        } else {
                            clientSock.close(); // string does not conform, ditch connection
                            break;
                        }
                    }
                }
            }
        }
        catch(IOException e){
        }
    }
}
