package com.parkingwang.lang;

/**
 * 过滤接口
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.3
 */
public interface Filter<T> {

    /**
     * 返回是否被接受此值
     * @param item 用于被测试的值
     * @return 是否接受此值
     */
    boolean test(T item);

}
