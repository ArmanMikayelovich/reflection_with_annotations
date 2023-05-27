import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class AnnotationTest {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        System.out.println(configuration);
        Method[] declaredMethods = Configuration.class.getDeclaredMethods();
        Map<Integer, Method> methodMap = new TreeMap<>();
        Arrays.stream(declaredMethods)
                .filter(declaredMethod -> {
                    InitMethod annotation = declaredMethod.getAnnotation(InitMethod.class);
                    return annotation != null;
                }).forEach(declaredMethod -> {
                    InitMethod annotation = declaredMethod.getAnnotation(InitMethod.class);
                    int sequentialIndex = annotation.sequentialIndex();
                    methodMap.put(sequentialIndex, declaredMethod);
                });
        System.out.println(methodMap);

        methodMap.entrySet().forEach( integerMethodEntry -> {
            Integer key = integerMethodEntry.getKey();
            System.out.println("method key:" + key);
            System.out.println(integerMethodEntry.getValue());
        });

        methodMap.entrySet().forEach(entry -> {
            Method method = entry.getValue();
            try {
                method.setAccessible(true);
                method.invoke(configuration, new Object[]{});
            } catch (IllegalAccessException e) {
                System.err.println(e.getMessage());
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(configuration);

    }

}

class Configuration {

    private String dataBaseConfig;
    private String networkConfig;

    @InitMethod(sequentialIndex = 2)
    private void init2() {
        System.out.println("init1");
        dataBaseConfig = "dbConnection";
    }

    @Deprecated
    @InitMethod(sequentialIndex = 1)
    private void init1() {
        System.out.println("init2");
        networkConfig = "localhost://";
    }
 @Deprecated
    @InitMethod(sequentialIndex = 4)
    private void init4() {
        System.out.println("init2");
        networkConfig = "localhost://";
    }

    private void foo() {
        System.out.println("dummy thing");
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "dataBaseConfig='" + dataBaseConfig + '\'' +
                ", networkConfig='" + networkConfig + '\'' +
                '}';
    }
}


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface InitMethod {

    int sequentialIndex();
}

