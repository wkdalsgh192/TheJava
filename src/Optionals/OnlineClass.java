package Optionals;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class OnlineClass {

    private Integer id;
    private String title;
    private boolean closed;
    private Progress progress;


    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public String getTitle() {
        return this.title;
    }

//    public Progress getProgress() {
//
//        /* 이런 식으로 checked exception을 던지게 되면 자바는 stack trace를 찍는다.
//          => 에러가 발생하기 전까지 어떠한 call stack을 거쳐서 에러가 발생하게 됬는 지에 대한 정보를 담는다
//         즉 그 자체로 리소스를 사용하므로, 필요한 경우에만 사용하는 게 좋다.*/
//        if (this.progress  == null) throw new IllegalStateException();
//
//        return this.progress;
//    }

   public Optional<Progress> getProgress() {
        return Optional.ofNullable(this.progress);
   }
}
