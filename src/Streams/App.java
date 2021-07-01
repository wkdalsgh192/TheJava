package Streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
         List<OnlineClass> springClasses = new ArrayList<>();
         springClasses.add(new OnlineClass(1, "spring boot", true));
         springClasses.add(new OnlineClass(2, "spring data jpa", true));
         springClasses.add(new OnlineClass(3,"spring mvc", false));
         springClasses.add(new OnlineClass(4, "spring core", false));
         springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        Predicate<OnlineClass> pred = (i) -> i.getTitle().startsWith("spring");
        List<OnlineClass> list = springClasses.stream().filter(pred).collect(Collectors.toList());
        list.forEach((i) -> System.out.println(i.getTitle()));

        System.out.println("close 되지 않은 수업");
//        Predicate<OnlineClass> pred1 = (i) -> !i.isClosed();
//        springClasses.stream().filter(pred1).forEach((i) -> System.out.println(i.getTitle()));
        springClasses.stream().filter(Predicate.not(OnlineClass::isClosed)).forEach(i -> System.out.println(i.getTitle()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                        .map(OnlineClass::getTitle)
                        .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java Test", true));
        javaClasses.add(new OnlineClass(7, "The Java Code Manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java 8 to 11", false));

        List<List<OnlineClass>> events = new ArrayList<>();
        events.add(springClasses);
        events.add(javaClasses);

        System.out.println("두 수업 목록에 있는 모든 수업 아이디 출력");
        Stream<List<OnlineClass>> stream = events.stream();

//        Function<List<OnlineClass>, Stream<Integer>> func = l -> l.stream().map(c -> c.getId());
//        stream.flatMap(func).forEach(System.out::println);
        stream.flatMap(l -> l.stream())
                .forEach(oc -> System.out.println(oc.getId()));


        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 10빼고 최대 10개만 출력");
        Stream.iterate(10, i -> i+1)
             .skip(10)
             .limit(10)
             .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는 지 확인");
        System.out.println(javaClasses.stream().anyMatch(c -> c.getTitle().contains("Test")));


        System.out.println("스프링 수업 중 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        springClasses.stream()
                .filter(oc -> oc.getTitle()
                .contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
    }
}
