package com.parkingwang.lang;

import org.jetbrains.annotations.NotNull;

/**
 * 用于返回值的抽象接口，带参数接口
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.3
 */
public interface PSupplier<T, P> {

    /**
     * 返回一个值
     * @return 值对象，非Null
     */
    @NotNull T call(P args);
}
