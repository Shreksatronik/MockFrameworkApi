package Main;

import Matching.ArgumentMathcers.ArgMatcher;

import java.lang.reflect.Method;
import java.util.List;


class Invocation {
    private final Object[] args;
    private final Method method;
    private final Object retObj;
    private final DelegationStrategy delegationStrategy;

    private final boolean withMatchers;
    private final List<ArgMatcher> ArgumentMatchersList;

    private final Throwable toThrow;

    Invocation(Method method, Object[] args, Object retObj, List<ArgMatcher> ArgumentMatchersList) {
        this.args = args;
        this.method = method;
        this.retObj = retObj;
        this.delegationStrategy = DelegationStrategy.RETURN_CUSTOM;

        this.ArgumentMatchersList = ArgumentMatchersList;
        this.withMatchers = !(ArgumentMatchersList == null);

        this.toThrow = null;
    }

    Invocation(Method method, Object[] args, Object retObj) {
        this.args = args;
        this.method = method;
        this.retObj = retObj;
        this.delegationStrategy = DelegationStrategy.RETURN_CUSTOM;

        this.ArgumentMatchersList = null;
        this.withMatchers = false;

        this.toThrow = null;
    }

    Invocation(Method method, Object[] args, List<ArgMatcher> localArgumentMatchersList) {
        this.args = args;
        this.method = method;
        this.retObj = null;
        this.delegationStrategy = DelegationStrategy.CALL_REAL_METHOD;

        this.ArgumentMatchersList = localArgumentMatchersList;
        this.withMatchers = !(localArgumentMatchersList == null);

        this.toThrow = null;
    }

    Invocation(Method method, Object[] args) {
        this.args = args;
        this.method = method;
        this.retObj = null;
        this.delegationStrategy = DelegationStrategy.CALL_REAL_METHOD;

        this.ArgumentMatchersList = null;
        this.withMatchers = false;

        this.toThrow = null;
    }

    Invocation(Method method, Object[] args, List<ArgMatcher> localArgumentMatchersList, Throwable toThrow) {
        this.args = args;
        this.method = method;
        this.retObj = null;
        this.delegationStrategy = DelegationStrategy.THROW_EXCEPTION;

        this.ArgumentMatchersList = localArgumentMatchersList;
        this.withMatchers = !(localArgumentMatchersList == null);

        this.toThrow = toThrow;
    }

    Invocation(Method method, Object[] args, Throwable toThrow) {
        this.args = args;
        this.method = method;
        this.retObj = null;
        this.delegationStrategy = DelegationStrategy.THROW_EXCEPTION;

        this.ArgumentMatchersList = null;
        this.withMatchers = false;

        this.toThrow = toThrow;
    }

    Object[] getArgs() {
        return args;
    }

    Method getMethod() {
        return method;
    }

    Object getRetObj() {
        return retObj;
    }

    List<ArgMatcher> getLocalArgumentMatchersList() {
        return ArgumentMatchersList;
    }

    boolean isWithMatchers() {
        return withMatchers;
    }

    DelegationStrategy getDelegationStrategy() {
        return delegationStrategy;
    }

    Throwable getToThrow() {
        return toThrow;
    }

}