package com.parkingwang.lang;

import com.parkingwang.lang.kit.ObjectKit;

import java.util.NoSuchElementException;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
public class Optional<T> {
    
    private static final Optional<?> EMPTY = new Optional();
    
    public static <T> Optional<T> of(T value) {
        return new Optional<T>(value);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> empty() {
        return (Optional<T>) EMPTY;
    }
    
    private final T mValue;

    private Optional() {
        this.mValue = null;
    }
    
    private Optional(T value) {
        this.mValue = ObjectKit.notNull(value);
    }
    
    public T get() {
        if (mValue == null) {
            throw new NoSuchElementException("No value present");
        }
        return mValue;
    }

    public T orElse(T other) {
        return mValue != null ? mValue : other;
    }

    public boolean isPresent() {
        return mValue != null;
    }

    public boolean isNotPresent(){
        return !isPresent();
    }

    public void ifPresent(Consumer<T> consumer){
        ObjectKit.notNull(consumer);
        if (mValue != null) {
            consumer.call(mValue);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Optional)) {
            return false;
        }

        Optional<?> other = (Optional<?>) obj;
        return ObjectKit.equals(mValue, other.mValue);
    }
    
    @Override
    public int hashCode() {
        return ObjectKit.hashCode(mValue);
    }
    
    @Override
    public String toString() {
        return mValue != null
            ? String.format("Optional[%s]", mValue)
            : "Optional.empty";
    }
}