package Generics;

public class GenericsTypeOld<Object> {

    private Object t;

    public Object get() {return t;}

    public void set(Object t) {
        this.t = t;
    }

    public static void main(String[] args) {
        GenericsTypeOld type = new GenericsTypeOld();
        type.set("Pankaj");
        type.set(10);
        String str = (String) type.get();
    }
}
