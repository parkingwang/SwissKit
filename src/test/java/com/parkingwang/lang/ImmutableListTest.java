package com.parkingwang.lang;

import com.parkingwang.lang.data.ImmutableList;
import com.parkingwang.lang.kit.ListKit;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.8
 */
public class ImmutableListTest {

    @Test
    public void testCreate(){
        List<String> list = ListKit.arrayListOf("A", "B", "C");
        ImmutableList<String> il = ImmutableList.listOf(list);
        Assert.assertEquals("A", il.get(0));
        Assert.assertEquals("B", il.get(1));
        Assert.assertEquals("C", il.get(2));
    }

}
