package com.parkingwang.lang;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 延迟器加载实现，带参数接口
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.3
 */
public class ArgumentedLazy<T, A>{

    private final PSupplier<T, A> mSupplier;
    private final AtomicReference<T> mValue = new AtomicReference<>(null);

    public ArgumentedLazy(PSupplier<T, A> supplier) {
        this.mSupplier = supplier;
    }

    @NotNull
    public T get(A args){
        final T cached = mValue.get();
        if (cached == null) {
            final T newObj = mSupplier.call(args);
            if (mValue.compareAndSet(null, newObj)) {
                return newObj;
            }else{
                return mValue.get();
            }
        }else{
            return cached;
        }
    }

    /**
     * @return 返回是否已设置值
     */
    public boolean isPresent(){
        return null != mValue.get();
    }

    @Deprecated
    public boolean isSet(){
        return isPresent();
    }

    /**
     * @return 获取当前值
     */
    public T getPresent(){
        return mValue.get();
    }

    /**
     * 移除已加载的值
     */
    public void remove(){
        mValue.set(null);
    }

    public static <T, P> ArgumentedLazy<T, P> from(PSupplier<T, P> supplier) {
        return new ArgumentedLazy<>(supplier);
    }
}
