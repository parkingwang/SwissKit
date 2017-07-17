package com.parkingwang.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
public interface Predicate<T> {

    boolean test(T item);

}
