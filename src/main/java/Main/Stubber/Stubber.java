package Main.Stubber;
import Annotation.Info;

    public class Stubber<T> {

        public void thenReturn(T returnObject) {
            Info.getLastMockInvocationHandler().setReturnObject(returnObject);
        }

        public void invokeRealMethod() {
            Info.getLastMockInvocationHandler().setRealMethodInvocation();
        }

        public void thenThrow(Throwable throwable) {
            Info.getLastMockInvocationHandler().setException(throwable);
        }
    }

