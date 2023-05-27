import java.util.Arrays;
import java.util.Optional;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> stringClass = Class.forName("java.lang.Integer");
        Arrays.stream(stringClass.getFields()).forEach(System.out::println);
        System.out.println("\n");
        Arrays.stream(stringClass.getDeclaredFields()).forEach(System.out::println);
        foo();
    }


    @Deprecated
    static void foo(){}

}