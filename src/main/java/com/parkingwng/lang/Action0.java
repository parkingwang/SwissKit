package com.parkingwng.lang;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Action0<T> {

    @NotNull
    T invoke();

    public abstract class Void implements Action0<Void> {

        @Override
        final public Void invoke() {
            invoke0();
            return null;
        }

        public abstract void invoke0();
    }

    public abstract class String implements Action0<String> { }

    public abstract class Bool implements Action0<Boolean> { }

    public abstract class Int implements Action0<Integer> { }
}
