package com.parkingwng.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Action2<I, O> {
    O invoke(I arg);
}
