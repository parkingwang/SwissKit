package com.parkingwang.lang.kit;

import com.parkingwang.lang.Indexed;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * 给定字符串列表中，任意一个是空时，返回True
     *
     * @param array 给定字符串列表
     * @return 任意一个为空时，返回True；否则返回False表示全部非空。
     */
    public static boolean isAnyEmpty(Iterable<CharSequence> array) {
        for (CharSequence item : array) {
            if (isEmpty(item)) return true;
        }
        return false;
    }

    public static boolean isAnyEmpty(CharSequence... array) {
        return isAnyEmpty(Arrays.asList(array));
    }

    /**
     * 给定字符串列表中，全部为空时，返回True
     *
     * @param array 给定字符串列表
     * @return 全部一个为空时，返回True；否则返回False表示全部为空。
     */
    public static boolean isAllEmpty(Iterable<CharSequence> array) {
        for (CharSequence item : array) {
            if (isNotEmpty(item)) return false;
        }
        return true;
    }

    public static boolean isAllEmpty(CharSequence... array) {
        return isAllEmpty(Arrays.asList(array));
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
        CollectionKit.forEach(resources, new Indexed<T>() {
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

    /**
     * 是否为数值类型字符串
     *
     * @param input 输入字符串
     * @return 是否是数值类型
     */
    public static boolean isNumeric(String input) {
        char[] chars = input.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal loader any possible sign up front
        int start = (chars[0] == '-' || chars[0] == '+') ? 1 : 0;
        if (sz > start + 1) {
            if (chars[start] == '0' && chars[start + 1] == 'x') {
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for build qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid numeric (e.g. chars[0..5] = "1234E")
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no build qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (!allowSigns
                    && (chars[i] == 'd'
                    || chars[i] == 'D'
                    || chars[i] == 'f'
                    || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l'
                    || chars[i] == 'L') {
                // not allowing L loader an exponent
                return foundDigit && !hasExp;
            }
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }

}
