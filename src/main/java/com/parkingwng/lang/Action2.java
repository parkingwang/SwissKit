package com.parkingwng.lang;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Action2<I, O> {
    @NotNull
    O invoke(@NotNull I arg);
}
