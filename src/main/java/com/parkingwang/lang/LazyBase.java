package com.parkingwang.lang;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
abstract class LazyBase<T> {

    private final AtomicReference<T> mValue = new AtomicReference<>(null);

    protected T get(){
        return getWithArg(null);
    }

    protected T getWithArg(Object arg){
        final T cached = mValue.get();
        if (cached == null) {
            final T newObj = getNewValueFromSupplier(arg);
            if (mValue.compareAndSet(null, newObj)) {
                return newObj;
            }else{
                return mValue.get();
            }
        }else{
            return cached;
        }
    }

    public T getPresent() {
        return mValue.get();
    }

    public boolean isPresent(){
        return null != mValue.get();
    }

    public boolean isNotPresent(){
        return !isPresent();
    }

    public void remove(){
        mValue.set(null);
    }

    ////////

    protected abstract T getNewValueFromSupplier(Object arg);

}
