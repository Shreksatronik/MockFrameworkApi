package ru.nsu;

public class MockFramework {

    private static final MockCore mockCore;

    static {
        mockCore = new MockCore();
    }

    public static <T> T mock(Class<T> clazz) {
        return mockCore.mock(clazz);
    }

    public static <T> Stubber<T> when(T obj) {
        return mockCore.when(obj);
    }

}
