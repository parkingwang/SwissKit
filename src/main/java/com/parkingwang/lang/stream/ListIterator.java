package com.parkingwang.lang.stream;

import java.util.Iterator;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
class ListIterator<T> implements Iterator<T> {

    private final Iterable<? extends T> mIterable;
    private Iterator<? extends T> mIterator;

    public ListIterator(Iterable<? extends T> iterable) {
        this.mIterable = iterable;
    }

    @Override
    public boolean hasNext() {
        return getIterator().hasNext();
    }

    @Override
    public T next() {
        return getIterator().next();
    }

    @Override
    public void remove() {
        getIterator().remove();
    }

    private Iterator<? extends T> getIterator() {
        if (mIterator != null) {
            return mIterator;
        }
        mIterator = mIterable.iterator();
        return mIterator;
    }
}