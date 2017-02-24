package com.parkingwang.lang.data;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.7
 */
final public class LatchedValue<T> extends Latched<T> {

    @Deprecated
    public boolean isSet() {
        return isPresent();
    }

    @Deprecated
    public boolean isNotSet(){
        return !isPresent();
    }

    public void setValue(@NotNull T value){
        set(value);
    }

}
