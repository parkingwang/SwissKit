package com.parkingwng.lang.kit;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1
 */
final public class TimeKit {

    private TimeKit() { }

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String now() {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }
}
