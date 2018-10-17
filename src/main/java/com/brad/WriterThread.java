package com.brad;

class WriterThread implements Runnable {

    String name;
    ApplicationContext applicationContext;

    private final Thread t;
    private final boolean[] duplicateArray = new boolean[999999999 + 1];


    WriterThread(String threadname, ApplicationContext ctx) {
        name = threadname;
        this.applicationContext = ctx;

        for ( Integer i = 0 ; i < 999999 + 1; ++ i )
            duplicateArray[i] =false;

        t = new Thread(this, name);
        applicationContext.registerThread(t);
        t.start();
    }

    public void run() {

        while (! Thread.currentThread().isInterrupted() ) {
            String number = applicationContext.queue.poll();
            if (number != null) {
                if (duplicateArray[Integer.parseInt(number)]) {
                    applicationContext.trafficReport.incrementDuplicate();
                } else {
                    applicationContext.trafficReport.incrementRecieved();
                    duplicateArray[Integer.parseInt(number)] = true;
                }
            }
        }
    }
}


