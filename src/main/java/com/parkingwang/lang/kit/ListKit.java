package com.parkingwang.lang.kit;

import com.parkingwang.lang.Indexed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
final public class ListKit {

    private ListKit() {}

    public static <T> ArrayList<T> arrayListOf(T...items){
        if (items.length == 0) {
            throw new IllegalArgumentException("Initial item is required !");
        }
        return toArrayList(Arrays.asList(items));
    }

    public static <T> ArrayList<T> toArrayList(Collection<T> items) {
        return new ArrayList<>(items);
    }

    public static <T> void forEach(Collection<T> items, Indexed<T> indexed) {
        final List<T> list = toArrayList(items);
        for (int index = 0; index < list.size(); index++) {
            indexed.invoke(index, list.get(index));
        }
    }

}
