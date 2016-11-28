package com.parkingwng.lang;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.6
 */
public interface ThrowSupplier<T> {

    @NotNull T get() throws Throwable;
}
