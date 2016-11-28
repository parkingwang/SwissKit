package com.parkingwng.lang;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Indexed<T> {

    void invoke(int index, @NotNull T item);
}
