package Methods;

import java.util.Locale;

public interface Bar extends Foo {

    default void printNameUpperCase() {
        System.out.println("Bar");
    };
}
