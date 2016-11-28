package com.parkingwng.lang;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Accumulator<E> {

    @NotNull
    E invoke(@NotNull E e1, @NotNull E e2);
}