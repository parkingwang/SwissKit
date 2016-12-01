package com.parkingwang.lang;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Accumulator<T> {

    @NotNull T invoke(@NotNull T a, @NotNull T b);
}
