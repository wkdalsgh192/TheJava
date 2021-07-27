package Annotations;

import java.util.Arrays;

@Chicken1("Spicy")
@Chicken1("Galic")
public class App1 {

    public static void main(String[] args) {
        ChickenContainer chickenContainer = App1.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> System.out.println(c.value()));
    }
}
