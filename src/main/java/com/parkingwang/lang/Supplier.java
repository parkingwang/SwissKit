package com.parkingwang.lang;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.1
 */
public interface Supplier<T> {
    @NotNull T get();
}
