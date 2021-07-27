package Annotations;

public class App {

    public static void main(String[] args) {

    }


    static class FeelsLikeChicken<@Chicken T> { // Use When @Target(ElementType.TYPE_PARAMETER | TYPE_USE)

        // USE WHEN @TARGET(ElementType.TYPE_USE)
        public static <@Chicken C> void print(@Chicken C c) {

        }
    }

}
