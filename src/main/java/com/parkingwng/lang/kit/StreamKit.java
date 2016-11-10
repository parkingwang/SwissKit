package com.parkingwng.lang.kit;

import java.io.*;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1
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

    public static int copy(InputStream from, OutputStream to) throws IOException {
        return copy(new InputStreamReader(from), new OutputStreamWriter(to));
    }
}
