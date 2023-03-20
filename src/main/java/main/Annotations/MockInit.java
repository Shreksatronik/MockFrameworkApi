package main.Annotations;
import ru.nsu.MockFramework;
import java.lang.reflect.Field;

public class MockInit {
    public static void initMocks(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Mock.class)) {
                try {
                    field.setAccessible(true);
                    field.set(instance, MockFramework.mock(field.getType()));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}
