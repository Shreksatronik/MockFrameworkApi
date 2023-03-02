public interface ArgumentMatcher<T> {
    boolean matches(T argument);

    default Class<?> type() {
        return Void.class;
    }
}
