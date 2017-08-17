package com.parkingwang.lang.kit;

import com.parkingwang.lang.Indexed;

import java.util.*;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
final public class CollectionKit {

    private CollectionKit() {}

    public static <T> ArrayList<T> arrayListOf(T...items){
        if (items.length == 0) {
            return new ArrayList<>(0);
        }else{
            return toArrayList(Arrays.asList(items));
        }
    }

    public static <T> ArrayList<T> toArrayList(Collection<T> items) {
        if (items instanceof ArrayList) {
            return (ArrayList<T>) items;
        }else{
            return newArrayList(items);
        }
    }

    public static <T> ArrayList<T> newArrayList(Collection<T> items){
        return new ArrayList<>(items);
    }

    public static <T> HashSet<T> hashSetOf(T...items){
        final HashSet<T> set = new HashSet<>();
        set.addAll(Arrays.asList(items));
        return set;
    }

    public static <T> void forEach(Collection<T> items, Indexed<T> indexed) {
        final List<T> list = toArrayList(items);
        for (int index = 0; index < list.size(); index++) {
            indexed.invoke(index, list.get(index));
        }
    }

}
