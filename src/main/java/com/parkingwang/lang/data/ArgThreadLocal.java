package com.parkingwang.lang.data;

import com.parkingwang.lang.SupplierArg;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.5.0
 */
@Deprecated
public class ArgThreadLocal<T, A> extends ThreadLocalArg<T, A> {

    public ArgThreadLocal(SupplierArg<T, A> supplier) {
        super(supplier);
    }
}
