package com.parkingwang.lang.data;

import com.parkingwang.lang.Stream;
import com.parkingwang.lang.kit.ListKit;
import com.parkingwang.lang.kit.ObjectKit;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public class ImmutableList<E> implements Iterable<E> {

    private final E[] mElementData;
    private final int mSize;

    public ImmutableList(E[] data) {
        this.mElementData = ObjectKit.notNull(data);
        this.mSize = mElementData.length;
    }

    @NotNull
    public E get(int position) {
        return mElementData[position];
    }

    public int size(){
        return mElementData.length;
    }

    public boolean isEmpty() {
        return 0 == mSize;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public boolean contains(E o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(E o) {
        if (o == null) {
            for (int i = 0; i < mSize; i++)
                if (mElementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < mSize; i++)
                if (o.equals(mElementData[i]))
                    return i;
        }
        return -1;
    }

    @NotNull
    public ArrayList<E> toList(){
        return ListKit.arrayListOf(mElementData);
    }

    @NotNull
    public E[] toArray() {
        return Arrays.copyOf(mElementData, mSize);
    }

    @NotNull
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < mSize) {
            return (T[]) Arrays.copyOf(mElementData, mSize, a.getClass());
        }
        System.arraycopy(mElementData, 0, a, 0, mSize);
        if (a.length > mSize) {
            a[mSize] = null;
        }
        return a;
    }

    @Override
    public Object clone() {
        return new ImmutableList<>(Arrays.copyOf(mElementData, mSize));
    }

    @Override
    public String toString() {
        return Arrays.toString(mElementData);
    }

    @NotNull
    public ImmutableList<E> concat(ImmutableList<E> src){
        final List<E> data = ListKit.arrayListOf(mElementData);
        data.addAll(ListKit.arrayListOf(src.mElementData));
        return listOf(data);
    }

    public Stream<E> stream(){
        return Stream.arrayOf(mElementData);
    }

    @NotNull
    @SuppressWarnings("unchecked")
    public static <E> ImmutableList<E> listOf(Collection<E> data) {
        final E[] array = (E[]) data.toArray();
        return new ImmutableList<>(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutableList)) return false;

        ImmutableList<?> that = (ImmutableList<?>) o;

        if (mSize != that.mSize) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(mElementData, that.mElementData);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(mElementData);
        result = 31 * result + mSize;
        return result;
    }

    // Add for-each feature
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor;
        int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != mSize;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            int i = cursor;
            if (i >= mSize)
                throw new NoSuchElementException();
            Object[] elementData = mElementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
