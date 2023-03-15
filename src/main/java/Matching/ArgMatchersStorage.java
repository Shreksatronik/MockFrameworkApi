package Matching;

import Matching.ArgumentMathcers.ArgMatcher;

import java.util.Stack;

public class ArgMatchersStorage {
        private final Stack<ArgMatcher> matcherStack = new Stack<>();

        public void addMatcher(ArgMatcher argumentMatcher) {
            matcherStack.push(argumentMatcher);
        }

    }
