package com.brad;

public class TrafficReport {

    private long recieved;
    private long duplicates;
    private long total;

    TrafficReport(){

    }

    public synchronized void incrementTotal(){
        ++ total;
    }

    public synchronized void incrementDuplicate(){
        ++ duplicates;
    }

    public synchronized void incrementRecieved(){
        ++ recieved;
    }

    public synchronized Long getTotal(){
        return total;
    }

    public synchronized void emitReport(){
        System.out.println(String.format( "Received %d unique numbers, %d duplicates. Unique total: %d", recieved, duplicates, total));
        recieved = duplicates = 0;

    }
}
