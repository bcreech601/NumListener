package com.brad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class ListenerThread implements Runnable {

    private String name;
    private final Thread t;
    private final ThreadContext ctx;
    private final Pattern pattern;

    ListenerThread( String threadname, ThreadContext ctx){
        name = threadname;
        this.ctx = ctx;

        pattern = Pattern.compile("[0-9]{9,9}");
        t = new Thread(this, name);
        ctx.registerThread(t);
        t.start();
    }

    public void run() {

        try {
            while( true ) {
                Socket clientSock = ctx.socket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

                String inputLine;

                while ((inputLine = in.readLine()) != null ) {
                    if( inputLine.equals("terminate") ){
                        ctx.terminate();
                    }
                    else {
                        Matcher m = pattern.matcher(inputLine);
                        if (inputLine.length() == 9 && m.find()  && ! Thread.currentThread().isInterrupted() ) {
                            //enqueue
                            ctx.trafficReport.incrementTotal();
                            ctx.queue.add(inputLine);
                        } else {
                            // string does not conform, ditch connection
                            clientSock.close();
                            break;
                        }
                    }
                }
            }
        }
        catch(IOException e){
            System.out.println(name + "io exception");
        }

        System.out.println(name + " exiting.");
    }
}
