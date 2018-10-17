package com.brad;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.Charset;
import java.util.HashMap;

class WriterThread implements Runnable {

    String name;
    ThreadContext threadContext;

    private final Thread t;
    private final boolean[] duplicateArray = new boolean[999999999 + 1];


    WriterThread(String threadname, ThreadContext ctx) {
        name = threadname;
        this.threadContext = ctx;

        for ( Integer i = 0 ; i < 999999 + 1; ++ i )
            duplicateArray[i] =false;

        t = new Thread(this, name);
        t.start();
    }

    public void run() {
        while (true) {
            String number = threadContext.queue.poll();
            if (number != null) {
                if (duplicateArray[Integer.parseInt(number)]) {
                    threadContext.trafficReport.incrementDuplicate();
                } else {
                    threadContext.trafficReport.incrementRecieved();
                    duplicateArray[Integer.parseInt(number)] = true;
                }
            }
        }
    }

}


