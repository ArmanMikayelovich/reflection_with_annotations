import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class getAllFields {
    public static void main(String[] args) {
        List<Method> fieldList = new ArrayList<>();
        Class<D> type = D.class;
//        System.out.println("type toString: "+ type.toString());
//        System.out.println("type toGenericString: "+ type.toGenericString());
        List<Method> allFields = getAllFields(fieldList, type);
        for (Method field : allFields) {
            System.out.println("Method: " + field);
        }
    }

    public static List<Method> getAllFields(List<Method> fields, Class<?> type) {
        Method[] declaredFields = type.getMethods();
        List<Method> list = Arrays.asList(declaredFields);
        fields.addAll(list);

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}

class A {
    public Integer x;

    public Integer getX() {
        return x;
    }
}

class B extends A {
    protected String name;

    public String getName() {
        return name;
    }
}

class C extends B {
    Double aDouble;

    public Double getaDouble() {
        return aDouble;
    }
}

class D extends C {
    private Number number;

    public Number getNumber() {
        return number;
    }
}

class Product {

    public static ProductDescription productDescription = new ProductDescription();
    int price;

    public void foo() {

    }
    public void bar() {

    }
}

class ProductDescription {
    int fieldCount = 1;
    int methodCount = 2;
}
