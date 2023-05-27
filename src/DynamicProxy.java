import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class DynamicProxy {
    public static void main(String[] args) {
        ClassLoader classLoader = DynamicProxy.class.getClassLoader();

        Class[] implementedInterfaces = {Map.class};

        Map<String, String> hashMapToForWrappingIntoProxy = new HashMap<>();
        Handler handler = new Handler(hashMapToForWrappingIntoProxy);

        Object proxyObject = Proxy.newProxyInstance(classLoader,
                implementedInterfaces,
                handler);

        Map<String, String> proxifiedMap = (Map<String, String>) proxyObject;
        long start = System.nanoTime();
        proxifiedMap.put("hello", "world");
        long end = System.nanoTime();
        System.out.println(end - start);
    }
}

 class Handler implements InvocationHandler {

    private final Map<String, Method> methods = new HashMap<>();

    private Object target;

    public Handler(Object target) {
        this.target = target;

        for(Method method: target.getClass().getDeclaredMethods()) {
            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy,  Method method, Object[] args)
            throws Throwable {
        long start = System.nanoTime();
        Method methodWithShouldBeInvoked = methods.get(method.getName());
        Object result = methodWithShouldBeInvoked.invoke(target, args);
        //result is here
        long elapsed = System.nanoTime() - start;

        System.out.printf("Executing {%s} finished in {%s} ns%n", method.getName(),
                elapsed);

        return result;
    }


     @Override
     public String toString() {
         return "Handler{" +
                 "methods=" + methods +
                 ", target=" + target +
                 '}';
     }
 }
