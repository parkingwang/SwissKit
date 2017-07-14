package com.parkingwang.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface ThrowAction<T> {

    T invoke() throws Throwable;

    public abstract class ThrowAction0 implements ThrowAction<Void> {

        @Override
        public Void invoke() throws Throwable {
            call();
            return null;
        }

        abstract public void call() throws Throwable;
    }
}
