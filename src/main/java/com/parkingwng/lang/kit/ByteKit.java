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

    public static byte[] fromLong(long value) {
        return new byte[] {
            (byte) (value & 0xff),
            (byte) ((value >> 8) & 0xff),
            (byte) ((value >> 16) & 0xff),
            (byte) ((value >> 24) & 0xff),
            (byte) ((value >> 32) & 0xff),
            (byte) ((value >> 40) & 0xff),
            (byte) ((value >> 48) & 0xff),
            (byte) ((value >> 56) & 0xff)
        };
    }

    public static long toLong(byte[] bytes) {
        return (0xffL & (long)bytes[0])
                | (0xff00L & ((long)bytes[1] << 8))
                | (0xff0000L & ((long)bytes[2] << 16))
                | (0xff000000L & ((long)bytes[3] << 24))
                | (0xff00000000L & ((long)bytes[4] << 32))
                | (0xff0000000000L & ((long)bytes[5] << 40))
                | (0xff000000000000L & ((long)bytes[6] << 48))
                | (0xff00000000000000L & ((long)bytes[7] << 56));
    }

    public static byte[] fromFloat(float value) {
        return fromInt(Float.floatToIntBits(value));
    }

    public static float toFloat(byte[] bytes) {
        return Float.intBitsToFloat(toInt(bytes));
    }

    public static byte[] fromDouble(double value) {
        return fromLong(Double.doubleToLongBits(value));
    }

    public static double toDouble(byte[] bytes) {
        return Double.longBitsToDouble(toLong(bytes));
    }

    public static byte[] sliceTo(byte[] source, int from, int to) {
        return Arrays.copyOfRange(source, from, to);
    }

    public static byte[] sliceBy(byte[] source, int index, int size) {
        return sliceTo(source, index, index + size);
    }
}
