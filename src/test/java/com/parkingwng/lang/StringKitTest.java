package com.parkingwng.lang;

import com.parkingwng.lang.kit.StringKit;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public class StringKitTest {

    private static final String ORIGIN = "abc.def.ghi";

    @Test
    public void testReplaceAll(){
        Assert.assertEquals("abc,def,ghi", StringKit.sreplace(ORIGIN, '.', ','));
    }

}
