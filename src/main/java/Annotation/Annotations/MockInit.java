package Annotation.Annotations;
import Main.MockFramework;
import java.lang.reflect.Field;

public class MockInit {
    public static void initMocks(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Mock.class)) {
                try {
                    field.setAccessible(true);
                    field.set(obj, MockFramework.mock(field.getType()));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}
