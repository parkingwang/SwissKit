package com.parkingwng.lang.kit;

import java.util.Arrays;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
final public class ByteKit {

    private ByteKit(){}

    private final static char[] HEX = "0123456789ABCDEF".toCharArray();

    public static String toHex(byte[] bytes) {
        final char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX[v >>> 4];
            hexChars[j * 2 + 1] = HEX[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] fromInt(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }

    public static int toInt(byte[] bytes) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException("Required 4 bytes");
        }
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;
        }
        return value;
    }

    public static byte[] sliceTo(byte[] source, int from, int to) {
        return Arrays.copyOfRange(source, from, to);
    }

    public static byte[] sliceBy(byte[] source, int index, int size) {
        return sliceTo(source, index, index + size);
    }
}
