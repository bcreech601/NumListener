# NumListener

Unique Nine Digin Number Aggregator
=======================================

A server process to receive and aggregate unique carriage return delimited 9 digit numbers sent from clients with TCP connection to port 4000 on the host macine 
The system is designed as a set of five listener threads, a writer thread and a main thread to provide periodic status reports communicating via a shared application context object. 

Maximum of 5 concurrent connections.

Clients can terminate the process by transmitting the string 'terminate'.

A deduplicated list of numbers received is written to a file called numbers.out.

To run unit test from the parent direcory run ...

```$xslt
mvn test
```



At a command prompt in the same directory as `pom.xml`, run this command...

```
mvn clean install
```

... this will create the application in the target directory, `NumListener-1.0-SNAPSHOT-jar-with-dependencies.jar`

To execute this application from the commandline run ...

```$xslt
java -cp target/NumListener-1.0-SNAPSHOT-jar-with-dependencies.jar com.brad.Main
```

