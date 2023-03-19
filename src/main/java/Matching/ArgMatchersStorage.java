package Matching;

import Matching.ArgumentMathcers.ArgMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ArgMatchersStorage {
        private final Stack<ArgMatcher> matcherStack = new Stack<>();

        public void addMatcher(ArgMatcher argumentMatcher) {
            matcherStack.push(argumentMatcher);
        }

    public List<ArgMatcher> pullMatchers() {
        if (matcherStack.isEmpty()) {
            return null;
        }
        return resetStack();
    }
    private List<ArgMatcher> resetStack() {
        ArrayList<ArgMatcher> lastMatchers = new ArrayList<>(matcherStack);
        matcherStack.clear();
        return lastMatchers;
    }
    public Stack<ArgMatcher> getMatcherStack() {
        return matcherStack;
    }

}
