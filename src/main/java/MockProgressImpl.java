@SuppressWarnings("unchecked")
public class MockProgressImpl implements MockProgress {

    private final ArgMatcherStorage argumentMatcherStorage = new ArgMatcherImpl();


    @Override
    public ArgMatcherStorage getArgumentMatcherStorage() {
        return argumentMatcherStorage;
    }
}
