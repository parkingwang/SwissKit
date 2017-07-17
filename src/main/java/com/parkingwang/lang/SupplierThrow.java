package com.parkingwang.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.6
 */
public interface SupplierThrow<T> {

    T call() throws Throwable;
}
