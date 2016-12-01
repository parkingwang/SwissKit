package com.parkingwang.lang;

import com.parkingwang.lang.kit.StringKit;
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
        Assert.assertEquals("abc,def,ghi", StringKit.creplace(ORIGIN, '.', ','));
    }

    @Test
    public void testSplit(){
        String template = "/service/$client_id/profile/$pid";
        String[] segments = StringKit.split(template, "/");
        Assert.assertArrayEquals(new String[]{"service", "$client_id", "profile", "$pid"}, segments);
    }

    @Test
    public void testSplit0(){
        String template = "/service/";
        String[] segments = StringKit.split(template, "/");
        Assert.assertArrayEquals(new String[]{"service"}, segments);
    }

    @Test
    public void testSplit1(){
        String template = "service/$client_id/";
        String[] segments = StringKit.split(template, "/");
        Assert.assertArrayEquals(new String[]{"service", "$client_id"}, segments);
    }
}
