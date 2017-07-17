package com.parkingwang.lang;

/**
 * 延迟器加载实现
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.1
 */
public class Lazy<T> extends LazyBase<T>{

    private final Supplier<T> mSupplier;

    public Lazy(Supplier<T> supplier) {
        this.mSupplier = supplier;
    }

    ////////

    @Override
    protected T getNewValueFromSupplier(Object o){
        return mSupplier.call();
    }

    @Override
    public T get() {
        return super.get();
    }

    ////////

    public static <T> Lazy<T> from(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }
}
