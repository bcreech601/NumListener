package com.brad;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.util.HashMap;

class WriterThread implements Runnable {

    String name;
    ThreadContext threadContext;

    private final Thread t;
    private final HashMap<String, Integer> duplicateMap = new HashMap<String, Integer>();

    BloomFilter<String> duplicationFilter = BloomFilter.create(new Funnel<String>() {
        @Override
        public void funnel(String number, PrimitiveSink into) {
            into.putChar(number.toCharArray()[0])
            .putChar(number.toCharArray()[1])
            .putChar(number.toCharArray()[2])
            .putChar(number.toCharArray()[3])
            .putChar(number.toCharArray()[4])
            .putChar(number.toCharArray()[5])
            .putChar(number.toCharArray()[6])
            .putChar(number.toCharArray()[7])
            .putChar(number.toCharArray()[8]);

        }
    }, 999999999, 0.01);

    WriterThread( String threadname, ThreadContext ctx){
        name = threadname;
        this.threadContext = ctx;

        t = new Thread(this, name);
        t.start();
    }

    public void run() {
        while( true ){
            String number = threadContext.queue.poll();
            if( number != null) {

                if( duplicationFilter.mightContain(number) ){

                    if( duplicateMap.containsKey(number) ) {
                        threadContext.trafficReport.incrementDuplicate();
                    }
                    else{
                        threadContext.trafficReport.incrementRecieved();
                        duplicateMap.put(number,1);
                    }
                }
                else{
                    threadContext.trafficReport.incrementRecieved();
                }

                duplicationFilter.put(number);
            }
        }

    }
}
