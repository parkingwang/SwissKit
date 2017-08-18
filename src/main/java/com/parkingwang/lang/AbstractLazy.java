package com.parkingwang.lang;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.8.0
 */
abstract class AbstractLazy<T> implements Present<T> {

    private final AtomicReference<T> mValue = new AtomicReference<>(null);

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

    @Override
    public void remove(){
        mValue.set(null);
    }

    @Override
    public void set(T value) {
        throw new UnsupportedOperationException("Not support");
    }

    @Override
    public T getPresent() {
        return getUnchecked();
    }

    @Override
    public boolean isPresent(){
        return null != mValue.get();
    }

    @Override
    public boolean isNotPresent(){
        return !isPresent();
    }

    @Override
    public T getChecked() {
        return getWithArg(null);
    }

    @Override
    public T getUnchecked() {
        return mValue.get();
    }

    @Override
    public void ifPresent(Consumer<T> consumer) {
        if (isPresent()){
            consumer.call(getUnchecked());
        }
    }

    @Override
    public T orElse(T elseValue) {
        return isPresent() ? getPresent() : elseValue;
    }

    ////////

    protected abstract T getNewValueFromSupplier(Object arg);

}
