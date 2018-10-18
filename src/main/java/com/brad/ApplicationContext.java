package com.brad;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ApplicationContext
{
    public ServerSocket socket;
    public ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
    public List<Thread> threadList = new ArrayList<Thread>();
    public final com.brad.TrafficReport trafficReport = new com.brad.TrafficReport();
    private boolean terminateCalled = false;

    void registerThread(Thread thread){
        threadList.add(thread);
    }

    void terminate(){
        for( Thread t : threadList){
            t.interrupt();
        }

        terminateCalled = true;
    }

    ApplicationContext(ServerSocket sock) throws FileNotFoundException, IOException{
        socket = sock;
    }

    public boolean isTerminateCalled(){
        return terminateCalled;
    }
}
