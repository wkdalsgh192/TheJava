package Streams;

import java.util.*;

public class App {

    public static void main(String[] args) {
         List<OnlineClass> springClasses = new ArrayList<>();
         springClasses.add(new OnlineClass(1, "spring boot", true));
         springClasses.add(new OnlineClass(2, "spring data jpa", true));
         springClasses.add(new OnlineClass(3,"spring mvc", false));
         springClasses.add(new OnlineClass(4, "spring core", false));
         springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");

        System.out.println("close 되지 않은 수업");

        System.out.println("수업 이름만 모아서 스트림 만들기");

    }
}
