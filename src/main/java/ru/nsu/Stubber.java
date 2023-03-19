package ru.nsu;
import main.Info;

    public class Stubber<T> {

        public void thenReturn(T retObj) {
            Info.getLastMockInvocationHandler().setRetObj(retObj);
        }

        public void invokeRealMethod() {
            Info.getLastMockInvocationHandler().setRealMethodInvocation();
        }

        public void thenThrow(Throwable throwable) {
            Info.getLastMockInvocationHandler().setThrowable(throwable);
        }
    }

