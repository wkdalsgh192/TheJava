package FunctionalProgramming;

public class Greeting {

    private String name;

    // empty constructor
    public Greeting() {};

    // constructor
    public Greeting(String name) {
        this.name = name;
    };

    public String getName() {
        return name;
    }

    // instance method
    public String hello(String name) {
        return "hello "+name;
    }

    // static method
    public static String hi(String name) {
        return "hi "+name;
    }
}
