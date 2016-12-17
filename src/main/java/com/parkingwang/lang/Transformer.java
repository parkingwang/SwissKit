package com.parkingwang.lang;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Transformer<I, O> {

    @NotNull O call(I in);

}
