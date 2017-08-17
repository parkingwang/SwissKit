package com.parkingwang.lang.kit;

import com.parkingwang.lang.Indexed;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
@Deprecated
final public class ListKit {

    public static <T> ArrayList<T> arrayListOf(T...items){
        return CollectionKit.arrayListOf(items);
    }

    public static <T> ArrayList<T> toArrayList(Collection<T> items) {
        return CollectionKit.toArrayList(items);
    }

    public static <T> ArrayList<T> newArrayList(Collection<T> items){
        return CollectionKit.newArrayList(items);
    }

    public static <T> void forEach(Collection<T> items, Indexed<T> indexed) {
        CollectionKit.forEach(items, indexed);
    }

}
