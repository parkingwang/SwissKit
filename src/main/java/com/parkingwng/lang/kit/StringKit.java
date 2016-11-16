package com.parkingwng.lang.kit;

import com.parkingwng.lang.Indexed;

import java.util.Collection;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
final public class StringKit {

    private StringKit() {}

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 小量文本替换
     * @param src 源数据
     * @param from 被替换字符
     * @param to 替换目标字符
     * @return 替换后的字符串
     */
    public static String sreplace(String src, char from, char to) {
        final char[] chars = src.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == from) {
                chars[i] = to;
            }
        }
        return new String(chars);
    }

    /**
     * 将一个集合转换成字符串。
     * @param resources 数据集合
     * @param chars 集合之间的拼接字符
     * @param <T> 数据类型
     * @return 字符串
     */
    public static <T> String join(Collection<T> resources, final String chars) {
        final StringBuilder buff = new StringBuilder();
        final int maxIndex = resources.size() - 1;
        ListKit.forEach(resources, new Indexed<T>() {
            @Override public void invoke(int index, T item) {
                buff.append(item);
                if (chars.length() > 0 && index < maxIndex) {
                    buff.append(chars);
                }
            }
        });
        return buff.toString();
    }

    /**
     * 将字节数组转换成十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     * @since 0.1.2
     */
    public static String hex(byte[] bytes) {
        return ByteKit.toHex(bytes);
    }
}
