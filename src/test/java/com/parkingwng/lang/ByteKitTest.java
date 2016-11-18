package com.parkingwng.lang;

import com.parkingwng.lang.kit.ByteKit;
import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public class ByteKitTest {

    final Random RANDOM = new Random();

    @Test
    public void testShort(){
        final short i = (short) RANDOM.nextInt(Integer.MAX_VALUE);
        byte[] bi = ByteKit.fromShort(i);
        Assert.assertEquals(i, ByteKit.toShort(bi));
    }

    @Test
    public void testInt(){
        final int i = RANDOM.nextInt(Integer.MAX_VALUE);
        byte[] bi = ByteKit.fromInt(i);
        Assert.assertEquals(i, ByteKit.toInt(bi));
    }

    @Test
    public void testLong(){
        final long l = RANDOM.nextLong();
        byte[] bl = ByteKit.fromLong(l);
        Assert.assertEquals(l, ByteKit.toLong(bl));
    }

    @Test
    public void testFloat(){
        final float v = RANDOM.nextFloat();
        byte[] bv = ByteKit.fromFloat(v);
        Assert.assertEquals(v, ByteKit.toFloat(bv), 0.0f);
    }

    @Test
    public void testDouble(){
        final double v = RANDOM.nextDouble();
        byte[] bv = ByteKit.fromDouble(v);
        Assert.assertEquals(v, ByteKit.toDouble(bv), 0.0f);
    }
}
