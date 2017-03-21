package com.parkingwang.lang.data;

import com.parkingwang.lang.ArgumentedSupplier;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.5.0
 */
public class ArgumentedThreadLocal<T, A> extends ThreadLocal<T> {

    private final AtomicReference<A> mArg = new AtomicReference<>();

    private final ArgumentedSupplier<T, A> mSupplier;

    public ArgumentedThreadLocal(ArgumentedSupplier<T, A> supplier) {
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

    @Override
    public void set(T value) {
        super.set(value);
    }

    @Override
    public void remove() {
        super.remove();
    }
}
