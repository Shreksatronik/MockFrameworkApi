package Matching;
import Annotation.Info;

import Matching.ArgumentMathcers.ArgMatcher;
import Matching.ArgumentMathcers.Equals;
import Matching.ArgumentMathcers.InstanceMatcher;

public class AnyAndEq {

    public static int anyInt() {
        putMatcher(new InstanceMatcher(Integer.class));
        return 0;
    }

    public static int eq(int num) {
        putMatcher(new Equals(num));
        return 0;
    }

    private static void putMatcher(ArgMatcher<?> argumentMatcher) {
       Info.getArgumentMatcherStorage().addMatcher(argumentMatcher);
    }

}
