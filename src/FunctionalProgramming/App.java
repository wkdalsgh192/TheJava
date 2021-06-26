package FunctionalProgramming;

import java.util.function.*;
import java.util.*;

public class App {

    public static void main(String[] args) {
        Function<Integer, String> foo = (i) -> "number";

        System.out.println(foo.apply(1));

        // refer to the static method of Greeting class
        UnaryOperator<String> hi = Greeting::hi;

        // refer to the instance method of Greeting
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;

        // refer to the default and custom constructor
        Supplier<Greeting> emptyGreeting = Greeting::new;
        Function<String, Greeting> simpleGreeting = Greeting::new;

        Greeting minho = simpleGreeting.apply("minho");
        System.out.println(minho.getName());

        // refer to
        String[] arr = {"minho", "tiger", "alue"};
        Arrays.sort(arr, String::compareTo);

        System.out.println(Arrays.toString(arr));

    }
}
