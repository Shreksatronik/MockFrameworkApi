package Annotation;

import Matching.ArgMatchersStorage;
import Main.MockInvocationHandler;

public class Info {

    private static final ArgMatchersStorage argumentMatcherStorage;
    private static MockInvocationHandler lastMockInvocationHandler;

        static {
            argumentMatcherStorage = new ArgMatchersStorage();
        }

        public static ArgMatchersStorage getArgumentMatcherStorage() {
            return argumentMatcherStorage;
        }


    public static MockInvocationHandler getLastMockInvocationHandler() {
        return lastMockInvocationHandler;
    }

    public static void setLastMockInvocationHandler(MockInvocationHandler handler) {
        lastMockInvocationHandler = handler;
    }


}
