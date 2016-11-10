package com.parkingwng.lang;

import com.parkingwng.lang.kit.ListKit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1
 */
final public class S2<E> {

    private Collection<E> data;

    private S2(){}

    private S2(Collection<E> data){
        this.data = data;
    }

    public static <T> S2<T> from(T...items) {
        final S2<T> s2 = new S2<>(Arrays.asList(items));
        for (T item : items) {
            s2.data.add(item);
        }
        return s2;
    }

    @NotNull
    public S2<E> filter(Filter<E> filter) {
        data = filter(data, filter);
        return this;
    }

    @NotNull
    public <O> S2<O> map(Transformer<E, O> transformer) {
        final S2<O> s2 = new S2<>();
        s2.data = map(data, transformer);
        return s2;
    }

    public E firstOrNull(){
        if (data.isEmpty()) {
            return null;
        }else{
            return toList().get(0);
        }
    }

    public E lastOrNull() {
        if (data.isEmpty()) {
            return null;
        }else{
            return toList().get(data.size() - 1);
        }
    }

    @NotNull
    public List<E> toList(){
        return ListKit.toArrayList(data);
    }

    @NotNull
    public E[] toArray(){
        return (E[]) data.toArray();
    }

    @NotNull
    public static <T> ArrayList<T> listOf(T...items) {
        final ArrayList<T> output = new ArrayList<>(items.length);
        for (T item : items) {
            output.add(item);
        }
        return output;
    }

    @NotNull
    public static <T> Collection<T> filter(Collection<T> items, Filter<T> action) {
        final List<T> output = new ArrayList<>();
        for (T item : items) {
            if (action.filter(item)) {
                output.add(item);
            }
        }
        return output;
    }

    @NotNull
    public static <I, O> Collection<O> map(Collection<I> items, Transformer<I, O> action) {
        final List<O> output = new ArrayList<>();
        for (I in : items) {
            output.add(action.transform(in));
        }
        return output;
    }

}
