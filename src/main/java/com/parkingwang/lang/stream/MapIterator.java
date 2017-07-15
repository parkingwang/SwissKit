package com.parkingwang.lang.stream;

import com.parkingwang.lang.Function;

import java.util.Iterator;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
class MapIterator<T, R> extends CheckedIterator<R> {

    private final Iterator<? extends T> mIterator;
    private final Function<? super T, ? extends R> mMapper;

    public MapIterator(Iterator<? extends T> iterator, Function<? super T, ? extends R> mapper) {
        this.mIterator = iterator;
        this.mMapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return mIterator.hasNext();
    }

    @Override
    public R nextIteration() {
        return mMapper.call(mIterator.next());
    }
}