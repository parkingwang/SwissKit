package com.parkingwang.lang.kit;

import com.parkingwang.lang.Indexed;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
     * 小量文本替换，替换单个字符
     * @param template 源数据
     * @param fromChar 被替换字符
     * @param toChar 替换目标字符
     * @return 替换后的字符串
     */
    public static String replaceChar(String template, char fromChar, char toChar) {
        final char[] chars = template.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == fromChar) {
                chars[i] = toChar;
            }
        }
        return new String(chars);
    }

    /**
     * 对字符串模块，使用指定的值来替换给定的全部Marker。
     * @param template 字符串模块，如  /service/api/${user_id}
     * @param marker 需要替换的Marker，如 ${user_id}
     * @param value 需要替换的值，如 yoojia
     * @return 返回替换Marker后的新字符串，如 /service/api/yoojia
     */
    public static String replaceMarker(String template, String marker, Object value){
        int foundIndex = template.indexOf(marker);
        if (foundIndex == -1) {
            return template;
        }else{
            int fromIndex = 0;
            final StringBuilder buff = new StringBuilder(template.substring(fromIndex, foundIndex));
            buff.append(value);
            while (true){
                fromIndex = foundIndex + marker.length();
                foundIndex = template.indexOf(marker, fromIndex);
                if (fromIndex == foundIndex || -1 == foundIndex){
                    buff.append(template.substring(fromIndex, template.length()));
                    break;
                }else{
                    buff.append(template.substring(fromIndex, foundIndex));
                    buff.append(value);
                }
            }
            return buff.toString();
        }
    }

    public static String[] split(String template, String separator){
        final List<String> output = new ArrayList<>();
        int index = template.indexOf(separator);
        int preIndex = 0;
        while (index != -1){
            if (preIndex != index){
                output.add(template.substring(preIndex, index));
            }
            index++;
            preIndex = index;
            index = template.indexOf(separator, index);
        }
        if (preIndex < template.length()){
            output.add(template.substring(preIndex));
        }
        return output.toArray(new String[output.size()]);
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

    /**
     * 从输入流中读取字符串
     * @param in 输入流
     * @return 字符串
     * @throws IOException
     */
    public static String read(InputStream in) throws IOException {
        return ByteKit.readString(in);
    }
}
