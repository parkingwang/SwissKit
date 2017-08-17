package com.parkingwang.lang.data;

import com.parkingwang.lang.ArgSupplier;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.5.0
 */
public class ArgThreadLocal<T, A> extends ThreadLocal<T> {

    private final AtomicReference<A> mArg = new AtomicReference<>();

    private final ArgSupplier<T, A> mSupplier;

    public ArgThreadLocal(ArgSupplier<T, A> supplier) {
        mSupplier = supplier;
    }

    public T get(A arg){
        mArg.set(arg);
        return super.get();
    }

    @Override
    protected T initialValue() {
        return mSupplier.call(mArg.get());
    }

    @Override
    public T get() {
        throw new UnsupportedOperationException("Use get(arg) instead");
    }

}
