package com.parkingwang.lang.stream;

import com.parkingwang.lang.Predicate;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.7
 */
class FilterIterator<T> implements Iterator<T> {

    private final Iterator<? extends T> mIterator;
    private final Predicate<? super T> mPredicate;
    private boolean mHasNext;
    private boolean mHasNextEvaluated;
    private T next;

    public FilterIterator(Iterator<? extends T> iterator, Predicate<? super T> predicate) {
        this.mIterator = iterator;
        this.mPredicate = predicate;
    }

    @Override
    public boolean hasNext() {
        if (!mHasNextEvaluated) {
            nextIteration();
            mHasNextEvaluated = true;
        }
        return mHasNext;
    }

    @Override
    public T next() {
        if (!mHasNextEvaluated) {
            mHasNext = hasNext();
        }
        if (!mHasNext) {
            throw new NoSuchElementException();
        }
        mHasNextEvaluated = false;
        return next;
    }

    private void nextIteration() {
        while (mIterator.hasNext()) {
            next = mIterator.next();
            if (mPredicate.test(next)) {
                mHasNext = true;
                return;
            }
        }
        mHasNext = false;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }
}