package Optionals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3,"spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass springBoot = new OnlineClass(1, "spring boot", true);
        // Progress 가 현재 null이기 때문에 RuntimeException이 뜬다.
        // Duration studyDuration = springBoot.getProgress().getStudyDuration();

         /*
         이를 해결하기 위해 주로 다음의 방법을 사용했다.
         이러한 null checkin 은 종종 까먹을 수가 있고,
         null을 리턴하게 끔 하는 것 자체가 좋은 코드가 아니다.
        */
        // Progress progress = springBoot.getProgress();

        /*
        대신 Optional<T> 를 사용한다.
        */
        Optional<Progress> progress = springBoot.getProgress();
        if (progress != null) System.out.println(progress);


        Optional<OnlineClass> spring = springClasses.stream()
                                            .filter(oc -> oc.getTitle().startsWith("spring"))
                                            .findFirst();

        spring.ifPresent(oc -> System.out.println(oc.getTitle()));

        /*상수로 이미 만들어져있는 것들을 참고해서 사용할 때 더 적합*/
        OnlineClass onlineClass = spring.orElse(createNewClass());
        System.out.println(onlineClass.getTitle());

        /*동적으로 무언가를 만들어서 작업을 추가로 해야할 때는 더 적절함*/
        OnlineClass orElseGetClass = spring.orElseGet(App::createNewClass);
        System.out.println(onlineClass.getTitle());

        /*추가로 만들 게 없고 그냥 에러를 던질 때는 다음과 같이 사용*/
        OnlineClass orElseThrowClass = spring.orElseThrow(IllegalStateException::new);


        Optional<Integer> filterId = spring.map(OnlineClass::getId);
        System.out.println(filterId.isPresent());


        /*
        리턴 타입이 Optional 인 경우
        map()을 쓰면 Optional 안에 다시 Optional이 생긴다.
        이럴 때는 flatMap을 사용하면 좋다.
        */
        Optional<Optional<Progress>> progressMap = spring.map(OnlineClass::getProgress);
        Optional<Progress> getProgress = progressMap.orElse(Optional.empty());

        Optional<Progress> progressFlatMap = spring.flatMap(OnlineClass::getProgress);

    }

    private static OnlineClass createNewClass() {
        System.out.println("new class created!");
        return new OnlineClass(10, "spring java", false);
    }
}
