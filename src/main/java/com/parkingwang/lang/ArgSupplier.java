package com.parkingwang.lang;

/**
 * 用于返回值的抽象接口，带参数接口
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.3
 */
public interface ArgSupplier<T, A> {

    /**
     * 返回一个值
     * @return 值对象，非Null
     */
    T call(A args);
}