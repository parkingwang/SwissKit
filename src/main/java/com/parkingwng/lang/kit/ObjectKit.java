package com.parkingwng.lang.kit;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
final public class ObjectKit {

    private ObjectKit(){}

    @NotNull
    public static <T> T notNull(T arg) {
        return notNull(arg, "Object cannot be null");
    }

    @NotNull
    public static <T> T notNull(T arg, String failMessage){
        if (arg == null) {
            throw new NullPointerException(failMessage);
        }
        return arg;
    }

    public static boolean equals(Object a, Object b) {
        return a == b || a != null && a.equals(b);
    }

    public static int hashCode(Object arg) {
        return arg != null ? arg.hashCode() : 0;
    }

}
