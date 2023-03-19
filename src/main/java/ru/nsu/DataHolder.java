package ru.nsu;

import Matching.ArgumentMathcers.ArgMatcher;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


class DataHolder {
    private final Object[] args;
    private final Method method;
    private final Object retObj;
    private final DelegationStrategy delegationStrategy;

    private final boolean withMatchers;
    private final List<ArgMatcher> localArgumentMatchersList;

    private final Throwable toThrow;

    DataHolder(Method method, Object[] args, Object retObj, List<ArgMatcher> localArgumentMatchersList) {
        this.args = args;
        this.method = method;
        this.retObj = retObj;
        this.delegationStrategy = DelegationStrategy.RETURN_CUSTOM;

        this.localArgumentMatchersList = localArgumentMatchersList;
        this.withMatchers = !(localArgumentMatchersList == null);

        this.toThrow = null;
    }

    DataHolder(Method method, Object[] args, Object retObj) {
        this.args = args;
        this.method = method;
        this.retObj = retObj;
        this.delegationStrategy = DelegationStrategy.RETURN_CUSTOM;

        this.localArgumentMatchersList = null;
        this.withMatchers = false;

        this.toThrow = null;
    }

    DataHolder(Method method, Object[] args, List<ArgMatcher> localArgumentMatchersList) {
        this.args = args;
        this.method = method;
        this.retObj = null;
        this.delegationStrategy = DelegationStrategy.CALL_REAL_METHOD;

        this.localArgumentMatchersList = localArgumentMatchersList;
        this.withMatchers = !(localArgumentMatchersList == null);

        this.toThrow = null;
    }

    DataHolder(Method method, Object[] args) {
        this.args = args;
        this.method = method;
        this.retObj = null;
        this.delegationStrategy = DelegationStrategy.CALL_REAL_METHOD;

        this.localArgumentMatchersList = null;
        this.withMatchers = false;

        this.toThrow = null;
    }

    DataHolder(Method method, Object[] args, List<ArgMatcher> localArgumentMatchersList, Throwable toThrow) {
        this.args = args;
        this.method = method;
        this.retObj = null;
        this.delegationStrategy = DelegationStrategy.THROW_EXCEPTION;

        this.localArgumentMatchersList = localArgumentMatchersList;
        this.withMatchers = !(localArgumentMatchersList == null);

        this.toThrow = toThrow;
    }

    DataHolder(Method method, Object[] args, Throwable toThrow) {
        this.args = args;
        this.method = method;
        this.retObj = null;
        this.delegationStrategy = DelegationStrategy.THROW_EXCEPTION;

        this.localArgumentMatchersList = null;
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
        return localArgumentMatchersList;
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

    @Override
    public String toString() {
        return "DataHolder{" +
                "args=" + Arrays.toString(args) +
                ", method=" + method +
                ", retObj=" + retObj +
                ", delegationStrategy=" + delegationStrategy +
                ", withMatchers=" + withMatchers +
                ", localArgumentMatchersList=" + localArgumentMatchersList +
                ", toThrow=" + toThrow +
                '}';
    }
}