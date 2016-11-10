package com.parkingwng.lang;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1
 */
final public class FinalRef<T> {

    private T mRef;

    public FinalRef(T ref) {
        mRef = ref;
    }

    public void set(T ref) {
        mRef = ref;
    }

    public T get() {
        return mRef;
    }

    public boolean has(){
        return null != mRef;
    }

    public boolean notHas(){
        return null == mRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalRef<?> finalRef = (FinalRef<?>) o;
        return mRef != null ? mRef.equals(finalRef.mRef) : finalRef.mRef == null;
    }

    @Override
    public int hashCode() {
        return mRef != null ? mRef.hashCode() : 0;
    }
}
