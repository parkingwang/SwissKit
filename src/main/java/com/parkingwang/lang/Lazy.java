package com.parkingwang.lang;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.1
 */
public class Lazy<T>{

    private final Supplier<T> mSupplier;
    private final AtomicReference<T> mValue = new AtomicReference<>(null);

    public Lazy(Supplier<T> supplier) {
        this.mSupplier = supplier;
    }

    @NotNull
    public T get(){
        final T cached = mValue.get();
        if (cached == null) {
            final T newObj = mSupplier.call();
            if (mValue.compareAndSet(null, newObj)) {
                return newObj;
            }else{
                return mValue.get();
            }
        }else{
            return cached;
        }
    }

    public static <T> Lazy<T> from(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }
}
