package Matching.ArgumentMathcers;

public class InstanceMatcher implements ArgMatcher<Object> {

        private final Class<?> classs;

        public InstanceMatcher(Class<?> classs) {
            this.classs = classs;
        }

        @Override
        public boolean matches(Object arg) {
            return (arg != null) && classs.isAssignableFrom(arg.getClass());
        }

    }

