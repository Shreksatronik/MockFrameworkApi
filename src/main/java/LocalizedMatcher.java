@SuppressWarnings("unchecked")
    public class LocalizedMatcher {

        private final Equals matcher;

        public LocalizedMatcher(Equals matcher) {
            this.matcher = matcher;
        }


        public Equals getMatcher() {
            return matcher;
        }
    }
