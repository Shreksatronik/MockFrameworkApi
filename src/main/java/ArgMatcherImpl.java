import java.util.Stack;

public class ArgMatcherImpl implements ArgMatcherStorage {
    private final Stack<LocalizedMatcher> matcherStack = new Stack<>();

    @Override
    public void reportMatcher(Equals matcher) {
        matcherStack.push(new LocalizedMatcher(matcher));
    }
}
