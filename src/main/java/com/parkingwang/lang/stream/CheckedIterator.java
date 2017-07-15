package com.parkingwang.lang.stream;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
abstract class CheckedIterator<E> implements Iterator<E> {

    protected abstract E nextIteration();

    @Override
    public final E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return nextIteration();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation not supported");
    }
}