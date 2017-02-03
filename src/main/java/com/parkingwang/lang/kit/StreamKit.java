package com.parkingwang.lang.kit;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
final public class StreamKit {

    private StreamKit() {}

    public static int copy(Reader from, Writer to) throws IOException {
        final char[] buffer = new char[1024 * 4];
        int count = 0;
        int n;
        while ((n = from.read(buffer)) != -1) {
            to.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

}
