package com.parkingwang.lang;

/**
 * @author 陈小锅 (yoojiachen@gmail.com)
 * @since 2.8.0
 */
public interface Present<T> {

    boolean isPresent();
    boolean isNotPresent();

    void set(T value);
    void remove();

    T getPresent();
    T getChecked();
    T getUnchecked();

    void ifPresent(Consumer<T> consumer);

    T orElse(T elseValue);
}
