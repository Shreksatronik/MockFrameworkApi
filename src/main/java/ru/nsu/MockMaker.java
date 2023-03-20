package ru.nsu;

import net.bytebuddy.ByteBuddy;
import main.Info;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import java.lang.reflect.Field;

public class MockMaker {
        public static <T> T mock(Class<T> clazz, DelegationStrategy delegationStrategy) {
            Info.setLastMockInvocationHandler(new MockInvocationHandler(delegationStrategy));

            Class<? extends T> byteBuddy = new ByteBuddy()
                    .subclass(clazz)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(Info.getLastMockInvocationHandler()))
                    .make()
                    .load(ClassLoader.getSystemClassLoader())
                    .getLoaded();


            Objenesis objenesis = new ObjenesisStd();
            ObjectInstantiator<? extends T> thingyInstantiator = objenesis.getInstantiatorOf(byteBuddy);
            return thingyInstantiator.newInstance();
        }    }