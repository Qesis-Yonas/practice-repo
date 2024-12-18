package io.javabrains.reactiveworkshop;

import java.io.IOException;
import java.util.List;

public class Exercise3 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Get all numbers in the ReactiveSources.intNumbersFlux stream
        // into a List and print the list and its size
        // TODO: Write code here

        List<Integer> list = ReactiveSources.intNumbersFlux().toStream().toList();
        System.out.println("List is: " + list);
        System.out.println("List Size is: " + list.size());

        ReactiveSources.intNumbersFlux().subscribe(x -> System.out.println("x :" + x));

        /*
        The reason why the "ReactiveSources.intNumbersFlux().subscribe(x -> System.out.println("x :" + x));" is not printing anything is
        because all the elements of intNumberFlux has been consumed by the toStream. To make the above print something, we can fix it as follows
         If you want both the toStream() method and the subscribe() method to work, you need to create a hot source or use a Flux that can replay emissions:

Option 1: Use a Replayable Flux
java
Copy code
Flux<Integer> replayableFlux = ReactiveSources.intNumbersFlux().cache(); // Caches all emitted items

// Convert to a list
List<Integer> list = replayableFlux.toStream().toList();
System.out.println("List is: " + list);
System.out.println("List Size is: " + list.size());

// Subscribe to replay the items
replayableFlux.subscribe(x -> System.out.println("x :" + x));
         */

        System.out.println("Press a key to end");
        System.in.read();
    }

}
