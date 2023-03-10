import org.springframework.cglib.proxy.*;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MyMock {
    //mock, @Mock, when, thenReturn, thenThrow
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface MockField {

    }

    private static class CallInfo<T> {
        private String className;
        private String methodName;
        private Object[] arguments;
        private T result;

        public CallInfo(String className, String methodName, Object[]arguments) {
            this.className = className;
            this.methodName = methodName;
            this.arguments = arguments;
        }


        public void setResult(T t) {this.result = t;}

        public T getResult() {return result;}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CallInfo)) return false;
            CallInfo<?> callInfo = (CallInfo<?>) o;
            return Objects.equals(className, callInfo.className) && Objects.equals(methodName, callInfo.methodName) && Arrays.equals(arguments, callInfo.arguments);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(className, methodName);
            result = 31 * result + Arrays.hashCode(arguments);
            return result;
        }
    }

    private static ArrayList<CallInfo> calls = new ArrayList();
    private static ArrayList<CallInfo> setReturns = new ArrayList();
    private static List<Args> args;
    private static List<Args> argsList = new ArrayList<>();

    void setArgs(List<Args> args) {
        this.args = args;
    }

    public static void setReturn(Object oldR, Object newR) {
        CallInfo call = calls.get(calls.size() - 1);
        call.setResult(newR);
        setReturns.add(call);
        argsList = new ArrayList<>();
        if (!args.isEmpty()) {
            for (Args arg : args) {
                switch (arg.getType()) {
                    case ANY:
                        //somth happ
                    case EQ:
                        //something happening
                }
            }

        }
    }

    public static int anyNumerical() {
        argsList.add(new Args(ArgumentMatchers.ANY, 0));
        return 0;
    }

    public static char anyChar() {
        argsList.add(new Args(ArgumentMatchers.ANY, 'a'));
        return 'a';
    }

    public static boolean anyBool() {
        argsList.add(new Args(ArgumentMatchers.ANY, true));
        return true;
    }

    public static <T> T any() {
        argsList.add(new Args(ArgumentMatchers.ANY, null));
        return null;
    }

    public static <T> T eq(T obj) {
        argsList.add(new Args(ArgumentMatchers.EQ, obj));
        return obj;
    }

    private static class MyMethodWrapper<T> implements MethodInterceptor{
        T orig;
        MyMethodWrapper(T orig){ this.orig = orig;}
        @Override
        public Object intercept(Object obj, Method method, Object[] args,
                                MethodProxy proxy) throws Throwable
        {
            calls.add(new CallInfo(obj.getClass().getName(), method.getName(), args));
            if (setReturns.contains(new CallInfo(obj.getClass().getName(), method.getName(), args)))
                return setReturns.get(
                        setReturns.lastIndexOf(new CallInfo(obj.getClass().getName(), method.getName(), args))
                ).getResult();
            return null;
        }

    }

    public static <T> T mock(T obj, Class<T> aClass) {
        return (T) Enhancer.create(aClass, new MyMethodWrapper<>(obj));
    }
}
