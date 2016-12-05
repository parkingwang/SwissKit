package com.parkingwang.lang;


import com.parkingwang.lang.kit.StringKit;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public class StreamTest {

    @Test
    public void test(){
        final Stream<String> stream = Stream.of(1,2,3,4,5,6,7,8,9)
                .filter(new Filter<Integer>() {
                    @Override
                    public boolean filter(Integer item) {
                        return item % 2 == 0;
                    }
                })
                .map(new Transformer<Integer, String>() {
                    @Override
                    public String transform(Integer in) {
                        return "S" + in;
                    }
                });
        Assert.assertEquals("S2", stream.firstOrNull());
        Assert.assertEquals("S8", stream.lastOrNull());
        final List<String> output = stream.toList();
        final String content = StringKit.join(output, ",");
        Assert.assertEquals("S2,S4,S6,S8", content);
    }

    @Test
    public void restReduce(){
        final int sum = Stream.of(1,2,3,4,5,6,7,8,9,10).reduce(new Accumulator<Integer>() {
            @Override @NotNull
            public Integer invoke(@NotNull Integer e1, @NotNull Integer e2) {
                return e1 + e2;
            }
        });
        Assert.assertEquals(55, sum);

        final String reduce = Stream.of(1,2,3,4,5,6,7,8,9,10).map(new Transformer<Integer, String>() {
            @NotNull
            @Override
            public String transform(Integer in) {
                return String.valueOf(in);
            }
        }).reduce(new Accumulator<String>() {
            @Override @NotNull
            public String invoke(@NotNull String e1, @NotNull String e2) {
                return e1 + "," + e2;
            }
        });

        Assert.assertEquals("1,2,3,4,5,6,7,8,9,10", reduce);
    }
}
