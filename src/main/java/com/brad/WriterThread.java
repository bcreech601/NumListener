package com.brad;

class WriterThread implements Runnable {

    String name;
    ApplicationContext applicationContext;
    private final DedupeStrategy dedupeStrategy = new DedupeStrategy();
    private final Thread t;

    WriterThread(String threadname, ApplicationContext ctx) {
        name = threadname;
        this.applicationContext = ctx;

        t = new Thread(this, name);
        applicationContext.registerThread(t);
        t.start();
    }

    public void run() {

        while (! Thread.currentThread().isInterrupted() ) {
            String number = applicationContext.queue.poll();
            if (number != null) {
                if (dedupeStrategy.isDuplicate(number)) {
                    applicationContext.trafficReport.incrementDuplicate();
                } else {
                    applicationContext.trafficReport.incrementRecieved();
                    dedupeStrategy.addKey(number);
                }
            }
        }
    }
}


