package com.parkingwang.lang.kit;

import org.jetbrains.annotations.NotNull;

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

    public <T> T get(@NotNull String nameChain, T defValue){
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
        if (output != null) {
            return (T) output;
        }else{
            return defValue;
        }
    }

    private static Queue<Section> convert(String[] paths){
        final List<Section> output = new ArrayList<>(paths.length + 1);
        for (String p : paths){
            // app.keys[0].name -> app/keys/[0]/name
            // app -> app
            // app.keys[0] -> app/keys/[0]
            final int sIndex = p.indexOf("[");
            final int eIndex = p.indexOf("]", sIndex);
            if (sIndex > 0 && eIndex > 0) {
                final String name = p.substring(0, sIndex);
                output.add(new Section(false, 0, name));
                final String sIdx = p.substring(sIndex + 1, eIndex);
                final int index = Integer.valueOf(sIdx);
                output.add(new Section(true, index, ""));
            }else{
                output.add(new Section(false, 0, p));
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
