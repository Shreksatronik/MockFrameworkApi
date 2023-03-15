package Matching;
import main.Info;

import Matching.ArgumentMathcers.ArgMatcher;
import Matching.ArgumentMathcers.Equals;
import Matching.ArgumentMathcers.InstanceMatcher;

public class AnyAndEq {

    public static String anyString() {
        putMatcher(new InstanceMatcher(String.class));
        return "";
    }

    public static int anyInt() {
        putMatcher(new InstanceMatcher(Integer.class));
        return 0;
    }


    public static boolean anyBool() {
        putMatcher(new InstanceMatcher(Boolean.class));
        return true;
    }


    public static byte anyByte() {
        putMatcher(new InstanceMatcher(Byte.class));
        return 0;
    }

    public static int equalsTo(int num) {
        putMatcher(new Equals(num));
        return 0;
    }

    public static boolean equalsTo(boolean value) {
        putMatcher(new Equals(value));
        return true;
    }
    private static void putMatcher(ArgMatcher<?> argumentMatcher) {
       Info.getArgumentMatcherStorage().addMatcher(argumentMatcher);
    }

}
