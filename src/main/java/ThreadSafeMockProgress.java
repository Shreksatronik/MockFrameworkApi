public class ThreadSafeMockProgress {
        private static final ThreadLocal<MockProgress> MOCKING_PROGRESS_PROVIDER =
                new ThreadLocal<MockProgress>() {
                    @Override
                    protected MockProgress initialValue() {
                        return new MockProgressImpl();
                    }
                };

        private ThreadSafeMockProgress() {}

        public static MockProgress mockingProgress() {
            return MOCKING_PROGRESS_PROVIDER.get();
        }
    }
