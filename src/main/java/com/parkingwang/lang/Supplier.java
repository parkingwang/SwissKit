package com.parkingwang.lang;

/**
 * 用于返回值的抽象接口
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.1
 */
public interface Supplier<T> {

    /**
     * 返回一个值
     * @return 值对象，非Null
     */
    T call();
}
