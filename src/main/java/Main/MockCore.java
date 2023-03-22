package Main;

import Main.Stubber.Stubber;

public class MockCore {

    public <T> T mock(Class<T> clazz) {
        return MockMaker.mock(clazz, DelegationStrategy.RETURN_NULL);
    }

    public <T> Stubber<T> when(T obj) {
        return new Stubber<>();
    }
}