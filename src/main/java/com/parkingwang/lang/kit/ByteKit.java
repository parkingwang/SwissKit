package com.parkingwang.lang.kit;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 类似nio的byte工具，这里重新定义这些字节工具类，用于Android环境。
 * 在数据
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

    public static byte[] readBytes(InputStream in) throws IOException{
        return readStream(in).toByteArray();
    }

    public static String readString(InputStream in) throws IOException {
        return readStream(in).toString();
    }

    public static ByteArrayOutputStream readStream(InputStream in) throws IOException{
        final BufferedInputStream input = new BufferedInputStream(in);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        int read = input.read();
        while(read != -1) {
            output.write((byte) read);
            read = input.read();
        }
        return output;
    }

    public static byte[] fromShort(short value) {
        return new byte[] {
                (byte)(value & 0xFF),
                (byte)(value >> 8 & 0xFF),
        };
    }

    public static short toShort(byte[] bytes) {
        checkBytes(bytes, 2);
        return (short)(((bytes[0] & 0xFF)
                | bytes[1] << 8));
    }

    public static byte[] fromInt(int value) {
        return new byte[] {
                (byte) (value & 0xFF),
                (byte) (value >> 8 & 0xFF),
                (byte) (value >> 16 & 0xFF),
                (byte) (value >> 24 & 0xFF),
        };
    }

    public static int toInt(byte[] bytes) {
        checkBytes(bytes, 4);
        return  bytes[0] & 0xFF
                | (bytes[1] & 0xFF) << 8
                | (bytes[2] & 0xFF) << 16
                | (bytes[3] & 0xFF) << 24;
    }

    public static byte[] fromLong(long value) {
        return new byte[] {
            (byte) (value & 0xFF),
            (byte) ((value >> 8) & 0xFF),
            (byte) ((value >> 16) & 0xFF),
            (byte) ((value >> 24) & 0xFF),
            (byte) ((value >> 32) & 0xFF),
            (byte) ((value >> 40) & 0xFF),
            (byte) ((value >> 48) & 0xFF),
            (byte) ((value >> 56) & 0xFF)
        };
    }

    public static long toLong(byte[] bytes) {
        checkBytes(bytes, 8);
        return  (long)bytes[0] & 0xFF
                | ((long)(bytes[1] & 0xFF) << 8)
                | ((long)(bytes[2] & 0xFF) << 16)
                | ((long)(bytes[3] & 0xFF) << 24)
                | ((long)(bytes[4] & 0xFF) << 32)
                | ((long)(bytes[5] & 0xFF) << 40)
                | ((long)(bytes[6] & 0xFF) << 48)
                | ((long)(bytes[7] & 0xFF) << 56);
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

    private static void checkBytes(byte[] bytes, int shouldBe) {
        if (bytes.length != shouldBe) {
            throw new IllegalArgumentException("Required " + shouldBe + " bytes");
        }
    }
}
