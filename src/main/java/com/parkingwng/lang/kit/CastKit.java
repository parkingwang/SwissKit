package com.parkingwng.lang.kit;

import com.parkingwng.lang.Action2;

/**
 * 参考alibaba.fastjson的实现
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1
 */
final public class CastKit {

    private CastKit(){}

    public static String castString(Object value, String defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof String) {
            return (String) value;
        }
        throw new ClassCastException("Object cannot cast to String, object: " + value);
    }

    public static Boolean castBoolean(Object value, Boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Boolean) {
            return ((Boolean) value);
        }else{
            throw new ClassCastException("Object cannot cast to boolean, object: " + value);
        }
    }

    public static Integer castInt(final Object value, final Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return parseStringTo(value, defaultValue, new Action2<String, Integer>() {
            public Integer invoke(String arg) {
                return Integer.parseInt(arg);
            }
        }, new Action2<Object, Integer>() {
            public Integer invoke(Object arg) {
                if (arg instanceof Boolean) {
                    return ((Boolean) arg) ? 1 : 0;
                }
                throw new ClassCastException("Object cannot cast to Integer, object: " + value);
            }
        });
    }

    public static Long castLong(final Object value, final Long defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Long) {
            return (Long) value;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return parseStringTo(value, defaultValue, new Action2<String, Long>() {
            public Long invoke(String arg) {
                return Long.parseLong(arg);
            }
        }, new Action2<Object, Long>() {
            public Long invoke(Object arg) {
                if (arg instanceof Boolean) {
                    return ((Boolean) arg) ? 1L : 0;
                }
                throw new ClassCastException("Object cannot cast to Long, object: " + value);
            }
        });
    }

    public static Float castFloat(final Object value, final Float defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Float) {
            return (Float) value;
        }
        if (value instanceof Number) {
            return ((Number) value).floatValue();
        }
        return parseStringTo(value, defaultValue, new Action2<String, Float>() {
            public Float invoke(String arg) {
                return Float.parseFloat(arg);
            }
        }, new Action2<Object, Float>() {
            public Float invoke(Object arg) {
                if (arg instanceof Boolean) {
                    return ((Boolean) arg) ? 1f : 0;
                }
                throw new ClassCastException("Object cannot cast to Float, object: " + value);
            }
        });
    }

    public static Double castDouble(final Object value, final Double defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Double) {
            return (Double) value;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return parseStringTo(value, defaultValue, new Action2<String, Double>() {
            public Double invoke(String arg) {
                return Double.parseDouble(arg);
            }
        }, new Action2<Object, Double>() {
            public Double invoke(Object arg) {
                if (arg instanceof Boolean) {
                    return ((Boolean) arg) ? 1.0 : 0;
                }
                throw new ClassCastException("Object cannot cast to Double, object: " + value);
            }
        });
    }

    private static <T> T parseStringTo(Object value, T defaultValue, Action2<String, T> isAction, Action2<Object, T> elseAction) {
        if (value instanceof String) {
            String str = (String) value;
            if (str.length() == 0 || "null".equalsIgnoreCase(str)) {
                return defaultValue;
            }
            if (str.indexOf(',') != 0) {
                str = str.replaceAll(",", "");
            }
            return isAction.invoke(str);
        }
        return elseAction.invoke(value);
    }

}
