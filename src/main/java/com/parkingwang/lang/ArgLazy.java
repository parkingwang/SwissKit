package com.parkingwang.lang;

/**
 * 延迟器加载实现，带参数接口
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.3
 */
@Deprecated
public class ArgLazy<T, A> extends LazyArg<T, A> {

    public ArgLazy(ArgSupplier<T, A> supplier) {
        super(supplier);
    }
}
