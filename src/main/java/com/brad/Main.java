package com.brad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {



   // Received 50 unique numbers, 2 duplicates. Unique total: 567231

    public static void main(String[] args) throws IOException {

        ThreadContext ctx = new ThreadContext(new ServerSocket(4000));

        new WriterThread( "writer", ctx);

        new ListenerThread("1", ctx);
        new ListenerThread("2", ctx );
        new ListenerThread("3", ctx );
        new ListenerThread("4", ctx );
        new ListenerThread("5", ctx );

        try {
            while( true ) {
                Thread.sleep(1000 * 10);

                ctx.trafficReport.emitReport();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }

        System.out.println("Main thread exiting.");

    }
}
