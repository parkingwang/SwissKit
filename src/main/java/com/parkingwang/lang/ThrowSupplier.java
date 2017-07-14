package com.parkingwang.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.6
 */
public interface ThrowSupplier<T> {

    T call() throws Throwable;
}
