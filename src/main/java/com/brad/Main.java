package com.brad;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.charset.Charset;
import java.util.HashMap;


public class Main {

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
