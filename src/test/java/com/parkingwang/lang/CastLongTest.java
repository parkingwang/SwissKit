package com.parkingwang.lang;

import com.parkingwang.lang.kit.CastKit;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author 陈小锅 (yoojiachen@gmail.com)
 * @since 1.0.0
 */
public class CastLongTest {

    @Test
    public void test0() {
        final long val = CastKit.castLong("0", 1L);
        Assert.assertEquals(0L, val);
    }

    @Test
    public void test0A() {
        final long val = CastKit.castLong("0A", 1L);
        Assert.assertEquals(1L, val);
    }

    @Test
    public void test999() {
        final long val = CastKit.castLong("999", 1L);
        Assert.assertEquals(999, val);
    }

    @Test
    public void testStr() {
        final long val = CastKit.castLong("abc", 1L);
        Assert.assertEquals(1, val);
    }
}
