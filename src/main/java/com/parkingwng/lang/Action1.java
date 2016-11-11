package com.parkingwng.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Action1<T> {
    T invoke(T arg);
}
