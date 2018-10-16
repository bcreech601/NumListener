package com.brad;

import org.omg.PortableInterceptor.INACTIVE;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadContext
{
    public ServerSocket socket;
    public ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
    List<Thread> threadList = new ArrayList<Thread>();
    com.brad.TrafficReport trafficReport = new com.brad.TrafficReport();

    void registerThread(Thread thread){
        threadList.add(thread);
    }

    void terminate(){
        for( Thread t : threadList){
            t.interrupt();
        }
    }

    ThreadContext(ServerSocket sock){
        socket = sock;
    }
}
