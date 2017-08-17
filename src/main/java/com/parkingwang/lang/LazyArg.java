package com.parkingwang.lang;

/**
 * 延迟器加载实现，带参数接口
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
public class LazyArg<T, A> extends LazyBase<T> {

    private final SupplierArg<T, A> mSupplier;

    public LazyArg(SupplierArg<T, A> supplier) {
        this.mSupplier = supplier;
    }

    public T getByArg(A arg){
        return getWithArg(arg);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected T getNewValueFromSupplier(Object arg) {
        return mSupplier.call((A) arg);
    }

    public static <T, P> LazyArg<T, P> from(SupplierArg<T, P> supplier) {
        return new LazyArg<>(supplier);
    }
}
