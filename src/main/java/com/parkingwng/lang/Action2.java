package com.parkingwng.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1
 */
public interface Action2<I, O> {
    O invoke(I arg);
}
