package com.parkingwang.lang;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Action<T> {

    @NotNull T invoke();

    public abstract class Void implements Action<Void> {

        @Override
        final public Void invoke() {
            invoke0();
            return null;
        }

        public abstract void invoke0();
    }

    public abstract class String implements Action<String> { }

    public abstract class Bool implements Action<Boolean> { }

    public abstract class Int implements Action<Integer> { }
}
