package FunctionalProgramming;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Plus10 {
    public static void main(String[] args) {
        Function<Integer, Integer> funcA = (i) -> i+10;
        Function<Integer, Integer> funcB = (i) -> i*i;

        // the argument first goes to funcB and the output to funcA
        System.out.println(funcA.compose(funcB).apply(10)); // expected 110

        // the argument first goes to funcA and the output to funcB
        System.out.println(funcA.andThen(funcB).apply(10)); // expected 400

        UnaryOperator<Integer> funcC = (i) -> i+10;
        System.out.println(funcC.compose(funcB).apply(10));
    }
}
