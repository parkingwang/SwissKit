package com.parkingwang.lang.data;

import com.parkingwang.lang.SupplierArg;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
public class ThreadLocalArg<T, A> extends ThreadLocal<T> {

    private final AtomicReference<A> mArg = new AtomicReference<>();

    private final SupplierArg<T, A> mSupplier;

    public ThreadLocalArg(SupplierArg<T, A> supplier) {
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
