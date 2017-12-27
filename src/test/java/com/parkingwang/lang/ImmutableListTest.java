package com.parkingwang.lang;

import com.parkingwang.lang.data.ImmutableList;
import com.parkingwang.lang.kit.CollectionKit;
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
        List<String> list = CollectionKit.arrayListOf("A", "B", "C");
        ImmutableList<String> il = ImmutableList.listOf(list);
        for (String item : il){
            Assert.assertEquals(true, list.contains(item));
        }
    }

}
