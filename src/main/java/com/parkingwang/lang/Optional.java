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
    
    private final T value;

    private Optional() {
        this.value = null;
    }
    
    private Optional(T value) {
        this.value = ObjectKit.notNull(value);
    }
    
    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }
    
    public boolean isPresent() {
        return value != null;
    }

    public boolean isNotPresent(){
        return !isPresent();
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
        return ObjectKit.equals(value, other.value);
    }
    
    @Override
    public int hashCode() {
        return ObjectKit.hashCode(value);
    }
    
    @Override
    public String toString() {
        return value != null
            ? String.format("Optional[%s]", value)
            : "Optional.empty";
    }
}