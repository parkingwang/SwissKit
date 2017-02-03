package com.parkingwang.lang.kit;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 2.2
 */
public class MapReader {

    private final Map<String, Object> mMap;

    private MapReader(Map<String, Object> map){
        mMap = map;
    }

    public static MapReader of(@NotNull Map<String, Object> map){
        return new MapReader(ObjectKit.notNull(map));
    }

    public boolean getBoolean(@NotNull String nameChain, boolean defaultValue){
        return CastKit.castBoolean(get(nameChain), defaultValue);
    }

    public boolean getBooleanValue(@NotNull String nameChain){
        return getBoolean(nameChain, false);
    }

    public int getIntValue(@NotNull String nameChain){
        return getInt(nameChain, 0);
    }

    public int getInt(@NotNull String nameChain, int defaultValue){
        return CastKit.castInt(get(nameChain), defaultValue);
    }

    public long getLongValue(@NotNull String nameChain) {
        return getLong(nameChain, 0L);
    }

    public long getLong(@NotNull String nameChain, long defaultValue){
        return CastKit.castLong(get(nameChain), defaultValue);
    }

    public float getFloatValue(@NotNull String nameChain){
        return getFloat(nameChain, 0f);
    }

    public float getFloat(@NotNull String nameChain, float defaultValue){
        return CastKit.castFloat(get(nameChain), defaultValue);
    }

    public double getDoubleValue(@NotNull String nameChain){
        return getDouble(nameChain, 0.0);
    }

    public double getDouble(@NotNull String nameChain, double defaultValue){
        return CastKit.castDouble(get(nameChain), defaultValue);
    }

    public String getString(@NotNull String nameChain){
        return getString(nameChain, "");
    }

    public String getString(@NotNull String nameChain, String defaultValue){
        return CastKit.castString(get(nameChain), defaultValue);
    }

    public Map<String, Object> getObject(@NotNull String nameChain){
        return getObject(nameChain, new HashMap<String, Object>(0));
    }

    public Map<String, Object> getObject(@NotNull String nameChain, Map defaultValue){
        return CastKit.castMap(get(nameChain), defaultValue);
    }

    public List<Map> getArray(@NotNull String nameChain){
        return getArray(nameChain, new ArrayList<Map>(0));
    }

    public List getArray(@NotNull String nameChain, List defaultValue){
        return CastKit.castList(get(nameChain), defaultValue);
    }

    public <T> T getCasted(@NotNull String nameChain, T defValue){
        final Object value = get(nameChain);
        if (value == null){
            return defValue;
        }else{
            return (T) value;
        }
    }

    @Nullable
    public Object get(@NotNull String nameChain) {
        // app.keys[0].name -> app/keys/[0]/name
        // app -> app
        // app.keys[0] -> app/keys/[0]
        final Queue<Section> sections = convert(StringKit.split(nameChain, "."));
        Object output = null;
        Object temp = mMap;
        while (!sections.isEmpty()) {
            final Section sec = sections.poll();
            final Object stepVal;
            if (sec.isArray) {
                final List<Object> list = (List<Object>) temp;
                stepVal = list.get(sec.index);
            }else/* if (sec.isObject) */{
                final Map<String, Object> map = (Map<String, Object>) temp;
                stepVal = map.get(sec.name);
            }
            if (stepVal == null) {
                output = null;
                break;
            }else if (sections.isEmpty()){
                output = stepVal;
            }else{
                temp = stepVal;
            }
        }
        return output;
    }

    private static Queue<Section> convert(String[] paths){
        final List<Section> output = new ArrayList<>(paths.length + 1);
        for (String path : paths){
            // app.keys[0].name -> app/keys/[0]/name
            // app -> app
            // app.keys[0] -> app/keys/[0]
            final int sIndex = path.indexOf("[");
            final int eIndex = path.indexOf("]", sIndex);
            if (sIndex > 0 && eIndex > 0 && eIndex > sIndex) {
                final String name = path.substring(0, sIndex);
                output.add(new Section(false, 0, name));
                final String index = path.substring(sIndex + 1, eIndex);
                if (index.length() == 0){
                    throw new IllegalArgumentException("Illegal index of path: " + path);
                }
                output.add(new Section(true, Integer.valueOf(index), ""));
            }else{
                output.add(new Section(false, 0, path));
            }
        }
        return new ArrayBlockingQueue<>(output.size(), true, output);
    }

    private static class Section {

        public final boolean isObject;
        public final boolean isArray;
        public final int index;
        public final String name;

        private Section(boolean isArray, int index, String name) {
            this.isObject = !isArray;
            this.isArray = isArray;
            this.index = index;
            this.name = name;
        }
    }

}
