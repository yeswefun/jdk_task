package j8.test;

public class Simple {

    private long i;

    public Simple() {
        i = 1;
        System.out.println("simple.<init>");
    }

    public long get() {
        return i;
    }
}