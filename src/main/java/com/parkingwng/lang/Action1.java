package com.parkingwng.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1
 */
public interface Action1<T> {
    T invoke(T arg);
}
