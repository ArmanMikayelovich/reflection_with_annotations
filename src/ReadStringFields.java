import java.lang.reflect.Field;

public class ReadStringFields {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> personClass = Class.forName("java.lang.Integer");
        Field[] fields = personClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field name: " + field.getName());
        }
    }
}
