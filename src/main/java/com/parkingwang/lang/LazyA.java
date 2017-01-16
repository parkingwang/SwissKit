package com.parkingwang.lang;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 指定初始化参数的Lazy
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.2.1
 */
public class LazyA<A, R>{

    private final SupplierA<A, R> mSupplier;
    private final AtomicReference<R> mValue = new AtomicReference<>(null);

    public LazyA(SupplierA<A, R> supplier) {
        this.mSupplier = supplier;
    }

    @NotNull
    public R get(A args){
        final R cached = mValue.get();
        if (cached == null) {
            final R newObj = mSupplier.call(args);
            if (mValue.compareAndSet(null, newObj)) {
                return newObj;
            }else{
                return mValue.get();
            }
        }else{
            return cached;
        }
    }

    public void remove(){
        mValue.set(null);
    }

    public static <A, R> LazyA<A, R> from(SupplierA<A, R> supplier) {
        return new LazyA<>(supplier);
    }
}
