package com.parkingwang.lang;

import java.util.concurrent.CountDownLatch;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.6
 */
public final class Required<T> {

    private T mValue;
    private CountDownLatch mLatch;

    public Required() {
        reset();
    }

    public Required(T value) {
        reset();
        set(value);
    }

    public void set(T value){
        mValue = value;
        mLatch.countDown();
    }

    public void reset(){
        mValue = null;
        mLatch = new CountDownLatch(1);
    }

    public boolean isPresent(){
        return null != mValue;
    }

    public T getChecked(){
        if(mValue == null) {
            throw new NullPointerException();
        } else {
            return mValue;
        }
    }

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
