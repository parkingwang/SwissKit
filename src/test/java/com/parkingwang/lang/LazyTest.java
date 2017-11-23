package com.parkingwang.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public class LazyTest {

    @Test
    public void test(){

        final Lazy<String> lazy = Lazy.from(new Supplier<String>() {
            @Override
            public String call() {
                return "HAHA";
            }
        });

        Assert.assertEquals("HAHA", lazy.getChecked());
        Assert.assertEquals("HAHA", lazy.getChecked());
    }
}
