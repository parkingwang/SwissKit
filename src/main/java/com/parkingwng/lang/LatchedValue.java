package com.parkingwng.lang;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CountDownLatch;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
final public class LatchedValue<T> {

    private final CountDownLatch mLatch = new CountDownLatch(1);
    private T mRef;

    public LatchedValue(@NotNull T ref) {
        setValue(ref);
    }

    public boolean isSet() {
        return mLatch.getCount() == 0;
    }

    public boolean isNotSet(){
        return !isSet();
    }

    public synchronized void setValue(T ref) {
        if (!isSet()){
            mRef = ref;
            mLatch.countDown();
        }
    }

    @NotNull
    public T get() throws InterruptedException {
        mLatch.await();
        synchronized (this){
            return mRef;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LatchedValue<?> ref = (LatchedValue<?>) o;
        return mRef != null ? mRef.equals(ref.mRef) : ref.mRef == null;
    }

    @Override
    public int hashCode() {
        return mRef != null ? mRef.hashCode() : 0;
    }
}
