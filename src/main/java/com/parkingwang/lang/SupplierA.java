package com.parkingwang.lang;

import org.jetbrains.annotations.NotNull;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.2.1
 */
public interface SupplierA<A, R> {
    @NotNull
    R call(A args);
}
