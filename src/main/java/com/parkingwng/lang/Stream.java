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
        if (data.isEmpty()){
            throw new IllegalArgumentException("Stream NOT allow empty elements");
        }
        this.data = data;
    }

    @NotNull
    public static <T> Stream<T> of(T...items) {
        return new Stream<>(Arrays.asList(items));
    }

    @NotNull
    public static <T> Stream<T> arrayOf(T[] items) {
        return new Stream<>(Arrays.asList(items));
    }

    @NotNull
    public static <T> Stream<T> listOf(Collection<T> data){
        return new Stream<T>(data);
    }

    @NotNull
    public Stream<E> filter(Filter<E> filter) {
        data = filterWith(data, filter);
        return this;
    }

    @NotNull
    public <R> Stream<R> map(Transformer<E, R> transformer) {
        final Stream<R> stream = new Stream<>();
        stream.data = mapWith(data, transformer);
        return stream;
    }

    @NotNull
    public E reduce(Accumulator<E> action){
        final List<E> list = toList();
        final int size = list.size();
        E output = list.get(0);
        for (int i = 1; i < size; i++) {
            output = action.invoke(output, list.get(i));
        }
        return output;
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
    public static <T> Collection<T> filterWith(Collection<T> items, Filter<T> action) {
        final List<T> output = new ArrayList<>();
        for (T item : items) {
            if (action.filter(item)) {
                output.add(item);
            }
        }
        return output;
    }

    @NotNull
    public static <E_IN, E_OUT> Collection<E_OUT> mapWith(Collection<E_IN> items, Transformer<E_IN, E_OUT> action) {
        final List<E_OUT> output = new ArrayList<>();
        for (E_IN in : items) {
            output.add(action.transform(in));
        }
        return output;
    }

}
