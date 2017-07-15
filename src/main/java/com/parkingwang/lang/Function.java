package com.parkingwang.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
public interface Function<T, R> {

    R call(T in);

}
