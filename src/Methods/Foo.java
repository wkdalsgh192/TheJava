package Methods;

import java.util.Locale;

public interface Foo {

    void printName();

    /*
    * @implSpec
    * 이 구현체는 getName()으로 가져온 String을 대문자로 변환하여 출력한다.
    * */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase(Locale.ROOT));
    }


    String getName();
}
