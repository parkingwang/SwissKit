package com.parkingwang.lang;

import com.parkingwang.lang.kit.ObjectKit;

import java.util.concurrent.CountDownLatch;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.6
 */
public final class Required<T> implements Present<T> {

    private T mValue;
    private CountDownLatch mLatch;

    public Required() {
        remove();
    }

    public Required(T value) {
        remove();
        set(value);
    }

    public void set(T value){
        mValue = value;
        mLatch.countDown();
    }

    @Override
    public void remove(){
        mValue = null;
        mLatch = new CountDownLatch(1);
    }

    @Override
    public boolean isPresent(){
        return null != mValue;
    }

    @Override
    public boolean isNotPresent(){
        return !isPresent();
    }

    @Override
    public T getPresent() {
        return getUnchecked();
    }

    @Override
    public void ifPresent(Consumer<T> consumer){
        ObjectKit.notNull(consumer);
        if (isPresent()) {
            consumer.call(mValue);
        }
    }

    @Override
    public T orElse(T elseValue) {
        return isPresent() ? getPresent() : elseValue;
    }

    @Override
    public T getChecked(){
        if(mValue == null) {
            throw new NullPointerException("Value not set yet");
        } else {
            return mValue;
        }
    }

    @Override
    public T getUnchecked(){
        return mValue;
    }

    public T getAwaitChecked() throws InterruptedException {
        mLatch.await();
        return getChecked();
    }

    public T getAwaitUnchecked() throws InterruptedException {
        mLatch.await();
        return getUnchecked();
    }
}
