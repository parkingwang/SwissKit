package com.parkingwang.lang;

import org.jetbrains.annotations.NotNull;

/**
 * 执行动作并返回一个值的接口
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public interface Action<T> {

    /**
     * 执行动作，并返回一个非Null的值
     * @return 返回值，非Null
     */
    @NotNull T invoke();

}
