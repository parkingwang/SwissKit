package com.parkingwang.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Transformer<T, R> {

    R call(T in);

}
