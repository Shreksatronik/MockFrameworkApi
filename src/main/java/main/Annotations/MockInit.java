package main.Annotations;

import ru.nsu.MyMock;

import java.lang.reflect.Field;

public class MockInit {
    public static void initMocks(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Mock.class)) {
                try {
                    field.setAccessible(true);

                    field.set(instance, MyMock.mock(instance,field.getType()));

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
