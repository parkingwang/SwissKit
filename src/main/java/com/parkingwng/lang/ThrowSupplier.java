package com.parkingwng.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.6
 */
public interface ThrowSupplier<T> {
    T get() throws Throwable;
}
