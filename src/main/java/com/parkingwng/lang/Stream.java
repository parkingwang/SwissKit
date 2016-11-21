package com.parkingwng.lang;

import com.parkingwng.lang.kit.ListKit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
final public class Stream<E> {

    private Collection<E> data;

    private Stream(){}

    private Stream(Collection<E> data){
        this.data = data;
    }

    public static <T> Stream<T> arrayOf(T...items) {
        return new Stream<>(Arrays.asList(items));
    }

    public static <T> Stream<T> listOf(Collection<T> data){
        return new Stream<T>(data);
    }

    @NotNull
    public Stream<E> filter(Filter<E> filter) {
        data = filter(data, filter);
        return this;
    }

    @NotNull
    public <O> Stream<O> map(Transformer<E, O> transformer) {
        final Stream<O> stream = new Stream<>();
        stream.data = map(data, transformer);
        return stream;
    }

    public E firstOrNull(){
        if (isEmpty()) {
            return null;
        }else{
            return toList().get(0);
        }
    }

    public E lastOrNull() {
        if (isEmpty()) {
            return null;
        }else{
            return toList().get(data.size() - 1);
        }
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public boolean isNotEmpty(){
        return !isEmpty();
    }

    public int size(){
        return data.size();
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
