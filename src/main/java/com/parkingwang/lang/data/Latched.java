package com.parkingwang.lang.data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.3.3
 */
@Deprecated
public class Latched<T> {

    private CountDownLatch mLatch;
    private T mValue;

    public Latched() {
        reset();
    }

    public Latched(T value) {
        mLatch = new CountDownLatch(1);
        mValue = value;
    }

    public boolean isPresent() {
        return mLatch.getCount() == 0;
    }

    public boolean isNotPresent(){
        return !isPresent();
    }

    public synchronized void set(T value) {
        if (!isPresent()){
            mValue = value;
            mLatch.countDown();
        }
    }

    public T get() throws InterruptedException {
        mLatch.await();
        synchronized (this){
            return mValue;
        }
    }

    public T get(long timeout, TimeUnit unit) throws InterruptedException {
        mLatch.await(timeout, unit);
        synchronized (this){
            return mValue;
        }
    }

    public void reset(){
        mValue = null;
        mLatch = new CountDownLatch(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Latched<?> ref = (Latched<?>) o;
        return mValue != null ? mValue.equals(ref.mValue) : ref.mValue == null;
    }

    @Override
    public int hashCode() {
        return mValue != null ? mValue.hashCode() : 0;
    }
}
