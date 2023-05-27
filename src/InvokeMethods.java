import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeMethods {
    public static void main(String[] args) {
        try {
            Method justMethod = MathCalculator.class
                    .getDeclaredMethod("sum",
                    int.class, int.class);

            justMethod.setAccessible(true);

            MathCalculator obj = new MathCalculator(12);
            MathCalculator obj2 = new MathCalculator(20000);

            Object invoke = justMethod.invoke(obj2, 10, 20);

            System.out.println(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}

class MathCalculator {
    int x;

    MathCalculator(int x ) {
        this.x = x;
    }
    private int sum(int a, int b) {
        return a + x + b;
    }
}
