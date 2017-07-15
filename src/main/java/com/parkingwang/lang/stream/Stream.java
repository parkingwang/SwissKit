package com.parkingwang.lang.stream;

import com.parkingwang.lang.Function;
import com.parkingwang.lang.Optional;
import com.parkingwang.lang.Predicate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
public class Stream<T> {

    private final Iterator<? extends T> mIterator;

    private Stream(Iterator<? extends T> iterator) {
        this.mIterator = iterator;
    }

    public static <T> Stream<T> of(Iterable<T> elements){
        return new Stream<>(new ListIterator<>(elements));
    }

    public static <T> Stream<T> of(T... element){
        return new Stream<>(new ArrayIterator<>(element));
    }

    ////////

    public Stream<T> filter(Predicate<T> predicate){
        return new Stream<>(new FilterIterator<>(mIterator, predicate));
    }

    public <R> Stream<R> map(final Function<? super T, ? extends R> mapper) {
        return new Stream<>(new MapIterator<>(mIterator, mapper));
    }

    ////////

    public List<T> toList() {
        final List<T> result = new ArrayList<>();
        while (mIterator.hasNext()) {
            result.add(mIterator.next());
        }
        return result;
    }

    public Optional<T> findFirst() {
        if (mIterator.hasNext()) {
            return Optional.of(mIterator.next());
        }
        return Optional.empty();
    }
}
