package io.javabrains.reactiveworkshop;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        // TODO: Write code here
        StreamSources.intNumbersStream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        StreamSources.intNumbersStream().forEach(System.out::print);
        System.out.print("\n");
        // Print numbers from intNumbersStream that are less than 5
        // TODO: Write code here
        StreamSources.intNumbersStream().filter(x -> x < 5).forEach(System.out::print);
        System.out.print("\n");

        // Print the second and third numbers in intNumbersStream that's greater than 5
        // TODO: Write code here
        StreamSources.intNumbersStream().skip(1).limit(2).forEach(x -> System.out.print(x + " "));
        System.out.print("\n");

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        // TODO: Write code here
        StreamSources.intNumbersStream().filter(x -> x > 5).findFirst().ifPresentOrElse(System.out::println, () -> System.out.println(-1));
        System.out.print("\n");

        //Or else
        Integer firstInteger = StreamSources.intNumbersStream().filter(x -> x > 5).findFirst().orElse(-1);
        System.out.println(firstInteger);

        // Print first names of all users in userStream
        // TODO: Write code here
        StreamSources.userStream().map(User::getFirstName).forEach(System.out::println);

        // Print first names in userStream for users that have IDs from number stream
        // TODO: Write code here

        StreamSources.userStream()
                .filter(user -> StreamSources.intNumbersStream()
                        .anyMatch(y -> y == user.getId()))
                .map(User::getFirstName).forEach(System.out::println);

    }

}














/*
// Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        // TODO: Write code here
        System.out.println("Print all numbers in the intNumbersStream stream");
//        StreamSources.intNumbersStream().forEach(System.out::println);
        StreamSources.intNumbersStream().forEach(x -> System.out.print(x + " "));
        System.out.println();

        // Print numbers from intNumbersStream that are less than 5
        // TODO: Write code here
        System.out.println("Print numbers from intNumbersStream that are less than 5");
        StreamSources.intNumbersStream().filter(x -> x < 5).forEach(x -> System.out.print(x + " "));
        System.out.println();

        // Print the second and third numbers in intNumbersStream that's greater than 5
        // TODO: Write code here
        System.out.println("Print the second and third numbers in intNumbersStream that's greater than 5");
        StreamSources.intNumbersStream().filter(x -> x > 5).skip(1).limit(2).forEach(x -> System.out.print(x + " "));
        System.out.println();

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        // TODO: Write code here
        System.out.println("Print the first number in intNumbersStream that's greater than 5.");
        StreamSources.intNumbersStream().filter(x -> x > 5).findFirst().ifPresentOrElse(System.out::println, () -> System.out.println(-1));
        System.out.println();

        // Print first names of all users in userStream
        // TODO: Write code here
        System.out.println("Print first names of all users in userStream");
        StreamSources.userStream().forEach(x -> System.out.println(x.getFirstName()));
        StreamSources.userStream().map(User::getFirstName).forEach(System.out::println);

        // Print first names in userStream for users that have IDs from number stream
        // TODO: Write code here
        System.out.println("Print first names in userStream for users that have IDs from number stream");
        StreamSources.userStream().filter(x -> StreamSources.intNumbersStream()
                .anyMatch(y -> y == x.getId())).map((User::getFirstName)).forEach(System.out::println);
 */