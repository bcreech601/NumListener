package com.brad;

import java.io.IOException;
import java.net.ServerSocket;


public class Main {

    private static ApplicationContext applicationContext;

    private static void createListeners(Integer count){
        for( Integer i = 0 ; i < count; ++i ){
            new ListenerThread(i.toString(), applicationContext);
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(4000);
        applicationContext = new ApplicationContext(serverSocket);

        new WriterThread( "writer", applicationContext);

        createListeners(5);

        try {
            while( ! applicationContext.isTerminateCalled() ) {
                Thread.sleep(1000 * 10);

                applicationContext.trafficReport.emitReport();
            }

            serverSocket.close(); //  socketexception out of accept in listener threads

            for ( Thread thread: applicationContext.threadList ){ //if exiting from the join on all threads
                thread.join();
            }

        } catch (InterruptedException e) {

        }
    }
}
