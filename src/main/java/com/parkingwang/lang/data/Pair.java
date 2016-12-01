package com.parkingwang.lang.data;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.7
 */
public class Pair<A, B> {

    public final A first;
    public final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair<A, B> of(A a, B b){
        return new Pair<>(a, b);
    }
}
