package Generics;

public class GenericsType<T> {

    private T t;

    public T get() {
        return this.t;
    }

    public void set(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        GenericsType<String> type = new GenericsType<>();
        type.set("Pankaj");
        System.out.println(type.get());

        GenericsType<String> type1 = new GenericsType<>();
        type1.set("P");
        type1.set(10);
        String str = (String) type1.get();
        System.out.println(str);
    }


}
