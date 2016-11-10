package com.parkingwng.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1
 */
public interface Indexed<T> {

    void invoke(int index, T item);
}
