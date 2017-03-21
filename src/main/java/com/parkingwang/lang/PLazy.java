package com.parkingwang.lang;

/**
 * 延迟器加载实现，带参数接口
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.3
 */
public class PLazy<T, A> extends ArgumentedLazy<T, A> {

    public PLazy(PSupplier<T, A> supplier) {
        super(supplier);
    }
}
