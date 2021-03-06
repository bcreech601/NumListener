package com.brad;

public class TrafficReport {

    private long recieved;
    private long duplicates;
    private long total;

    public TrafficReport(){

    }

    public synchronized void incrementDuplicate(){
        ++ duplicates;
    }

    public synchronized void incrementRecieved(){
        ++ recieved;
        ++ total;
    }

    public Long getRecieved(){
        return recieved;
    }

    public Long getDuplicates(){
        return duplicates;
    }

    public synchronized Long getTotal(){
        return total;
    }

    public synchronized void emitReport(){
        System.out.println(String.format( "Received %d unique numbers, %d duplicates. Unique total: %d", recieved, duplicates, total));
        recieved = duplicates = 0;
    }
}
