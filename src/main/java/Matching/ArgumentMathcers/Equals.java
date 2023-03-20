package Matching.ArgumentMathcers;

public class Equals implements ArgMatcher<Object> {

        private final Object wanted;

        public Equals(Object wanted) {
            this.wanted = wanted;
        }

        @Override
        public boolean matches(Object arg) {
            return areEqual(wanted, arg);
        }

        private boolean areEqual(Object o1, Object o2) {
            if (o1 == o2) {
                return true;
            } else if (o1 == null || o2 == null) {
                return false;
            } else {
                return o1.equals(o2);
            }
        }

    }
