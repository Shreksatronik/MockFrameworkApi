package main;

import Matching.ArgMatchersStorage;

public class Info {

        private static final ArgMatchersStorage argumentMatcherStorage;


        static {
            argumentMatcherStorage = new ArgMatchersStorage();
        }

        public static ArgMatchersStorage getArgumentMatcherStorage() {
            return argumentMatcherStorage;
        }


}
