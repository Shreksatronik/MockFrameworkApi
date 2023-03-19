package ru.nsu;

import Matching.ArgumentMathcers.ArgMatcher;
import main.Info;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.concurrent.Callable;


public class MockInvocationHandler {

    private final List<DataHolder> dataHolders = new ArrayList<>();
    private Method lastMethod = null;
    private Object[] lastArgs = null;
    private final DelegationStrategy delegationStrategy;

    public MockInvocationHandler(DelegationStrategy delegationStrategy) {
        this.delegationStrategy = delegationStrategy;
    }

    @RuntimeType
    public Object invoke(@SuperCall Callable<?> zuper, @Origin Method method, @AllArguments Object[] args) throws Throwable {

        Info.setLastMockInvocationHandler(this);

        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        if (!stackTraceElements[2].toString().contains(stackTraceElements[3].getClassName())) {
            lastMethod = method;
            lastArgs = args;
        }

        // checks if the method was already called with the given arguments (without argument matchers)
        for (DataHolder dataHolder : dataHolders) {
            if (dataHolder.getMethod().equals(method) && Arrays.deepEquals(dataHolder.getArgs(), args)) {
                if (!dataHolder.isWithMatchers()) {
                    switch (dataHolder.getDelegationStrategy()) {
                        case CALL_REAL_METHOD:
                            return zuper.call();

                        case THROW_EXCEPTION:
                            throw dataHolder.getToThrow();

                        case RETURN_CUSTOM:
                            return dataHolder.getRetObj();

                    }
                }
            }
        }


        for (DataHolder dataHolder : dataHolders) {
            if (dataHolder.getMethod().equals(method)) {
                if (dataHolder.isWithMatchers()) {
                    if (Info.getArgumentMatcherStorage().getMatcherStack().empty()) {

                        boolean match;
                        for (int i = 0; i < args.length; i++) {

                            match = dataHolder.getLocalArgumentMatchersList().get(i).matches(lastArgs[i]);
                            if (!match) {
                                if (delegationStrategy == DelegationStrategy.CALL_REAL_METHOD)
                                    return zuper.call();
                                else
                                    return null;
                            }
                        }

                        switch (dataHolder.getDelegationStrategy()) {
                            case CALL_REAL_METHOD:
                                return zuper.call();

                            case THROW_EXCEPTION:
                                throw dataHolder.getToThrow();

                            case RETURN_CUSTOM:
                                return dataHolder.getRetObj();

                        }

                    } else {

                        return null;
                    }
                }
            }
        }

        if (delegationStrategy == DelegationStrategy.CALL_REAL_METHOD)
            return zuper.call();
        else
            return null;
    }

    public void setRetObj(Object retObj) {


        dataHolders.removeIf(dh -> dh.getMethod().equals(lastMethod) && Arrays.deepEquals(dh.getArgs(), lastArgs));


        List<ArgMatcher> argumentMatchersList = Info.getArgumentMatcherStorage().pullMatchers();

        if (argumentMatchersList != null) {
            // If argument matchers is not null, then create rule with matching
            if (argumentMatchersList.size() == lastArgs.length) {
                dataHolders.add(new DataHolder(lastMethod, lastArgs, retObj, argumentMatchersList));
            } else {
                throw new IllegalArgumentException("Use only ALL arguments as matchers, or ALL regular values");
            }

        } else {

            dataHolders.add(new DataHolder(lastMethod, lastArgs, retObj));
        }
    }

    public void setThrowable(Throwable throwable) {


        dataHolders.removeIf(dh -> dh.getMethod().equals(lastMethod) && Arrays.deepEquals(dh.getArgs(), lastArgs));


        List<ArgMatcher> argumentMatchersList = Info.getArgumentMatcherStorage().pullMatchers();

        if (argumentMatchersList != null) {

            if (argumentMatchersList.size() == lastArgs.length) {
                dataHolders.add(new DataHolder(lastMethod, lastArgs, argumentMatchersList, throwable));
            } else {
                throw new IllegalArgumentException("Use only ALL arguments as matchers, or ALL regular values");
            }

        } else {

            dataHolders.add(new DataHolder(lastMethod, lastArgs, throwable));
        }
    }

    public void setRealMethodInvocation() {

        dataHolders.removeIf(dh -> dh.getMethod().equals(lastMethod) && Arrays.deepEquals(dh.getArgs(), lastArgs));

        List<ArgMatcher> argumentMatchersList = Info.getArgumentMatcherStorage().pullMatchers();

        if (argumentMatchersList != null) {

            if (argumentMatchersList.size() == lastArgs.length) {
                dataHolders.add(new DataHolder(lastMethod, lastArgs, argumentMatchersList));
            } else {
                throw new IllegalArgumentException("Use only ALL arguments as matchers, or ALL regular values");
            }

        } else {

            dataHolders.add(new DataHolder(lastMethod, lastArgs));
        }
    }
}
