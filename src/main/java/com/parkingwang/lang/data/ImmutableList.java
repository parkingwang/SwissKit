package com.parkingwang.lang.data;

import com.parkingwang.lang.kit.ListKit;
import com.parkingwang.lang.kit.ObjectKit;

import java.util.*;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public class ImmutableList<E> {

    private final E[] mElementData;
    private final int mSize;

    public ImmutableList(E[] data) {
        this.mElementData = ObjectKit.notNull(data);
        this.mSize = mElementData.length;
    }

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

    public ArrayList<E> toList(){
        return ListKit.arrayListOf(mElementData);
    }

    public E[] toArray() {
        return Arrays.copyOf(mElementData, mSize);
    }

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

    public ImmutableList<E> concat(ImmutableList<E> src){
        final List<E> data = ListKit.arrayListOf(mElementData);
        data.addAll(ListKit.arrayListOf(src.mElementData));
        return listOf(data);
    }

    @SuppressWarnings("unchecked")
    public static <E> ImmutableList<E> listOf(Collection<E> data) {
        final E[] array = (E[]) data.toArray();
        return new ImmutableList<>(array);
    }

}
