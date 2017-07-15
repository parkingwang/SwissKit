package com.parkingwang.lang.stream;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
class ArrayIterator<E> extends CheckedIterator<E> {

    private final E[] mElements;
    private int mIndex;

    public ArrayIterator(E[] elements) {
        this.mElements = elements;
        mIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return mIndex < mElements.length;
    }

    public E nextIteration() {
        return mElements[mIndex++];
    }

}