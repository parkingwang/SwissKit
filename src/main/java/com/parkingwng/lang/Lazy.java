package com.parkingwng.lang;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.1
 */
public class Lazy<T> implements Supplier<T>{

    private final Supplier<T> mSupplier;
    private final AtomicReference<T> mValue = new AtomicReference<>(null);

    public Lazy(Supplier<T> mSupplier) {
        this.mSupplier = mSupplier;
    }

    @Override
    @NotNull
    public T get(){
        final T cached = mValue.get();
        if (cached == null) {
            final T newObj = mSupplier.get();
            if (mValue.compareAndSet(null, newObj)) {
                return newObj;
            }else{
                return mValue.get();
            }
        }else{
            return cached;
        }
    }
}
