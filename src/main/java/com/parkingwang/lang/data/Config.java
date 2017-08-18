package com.parkingwang.lang.data;

import com.parkingwang.lang.kit.CastKit;

import java.util.*;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0.0
 */
final public class Config {

    private final Map<String, Object> mDataMap;

    public Config(Map<String, Object> map) {
        this.mDataMap = map;
    }

    public Config(){
        this(new HashMap<String, Object>());
    }

    public void copyFrom(Config src) {
        mDataMap.putAll(src.mDataMap);
    }

    public boolean isEmpty(){
        return mDataMap.isEmpty();
    }

    public boolean isNotEmpty(){
        return !isEmpty();
    }

    public boolean containsName(String name){
        return mDataMap.containsKey(name);
    }

    public boolean notContainsName(String name){
        return !containsName(name);
    }

    public boolean containsNames(Set<String> names){
        for (String name : names){
            if (notContainsName(name)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return mDataMap.toString();
    }

    public KeyMap getDataMap(){
        return new KeyMap(mDataMap);
    }

    @SuppressWarnings("unchecked")
    public Config getConfig(String name) {
        final Object obj = mDataMap.get(name);
        if(obj != null) {
            if (obj instanceof Map) {
                return new Config((Map<String, Object>) obj);
            }else{
                throw new IllegalArgumentException("Name(" + name +") is NOT a <config> section !");
            }
        }else{
            final Map<String, Object> def = Collections.emptyMap();
            return new Config(def);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Config> getConfigList(String name) {
        final Object obj = mDataMap.get(name);
        final List<Config> output = new ArrayList<>();
        if(obj != null) {
            if (obj instanceof List) {
                final List<Object> items = (List<Object>) obj;
                for (Object item : items) {
                    if (item instanceof Map) {
                        output.add(new Config((Map<String, Object>) item));
                    }else{
                        throw new IllegalArgumentException("Config item : " + item +" is NOT a <config> section !");
                    }
                }
            }else{
                throw new IllegalArgumentException("Name(" + name +") is NOT a LIST <config> section !");
            }
        }
        return output;
    }

    @SuppressWarnings("unchecked")
    public List<String> getStringList(String name){
        Object value;
        value = (((value = mDataMap.get(name)) != null) || mDataMap.containsKey(name))
                ? value
                : new ArrayList<String>(0);
        return (List<String>) value;
    }

    public String getString(String name, String def){
        return CastKit.castString(mDataMap.get(name), def);
    }

    public String getString(String name){
        return getString(name, "");
    }

    public int getInt(String name, int def) {
        return CastKit.castInt(mDataMap.get(name), def);
    }

    public int getInt(String name) {
        return getInt(name, 0);
    }

    public long getLong(String name, long def) {
        return CastKit.castLong(mDataMap.get(name), def);
    }

    public long getLong(String name) {
        return getLong(name, 0L);
    }

    public short getShort(String name, short def) {
        return (short) getInt(name, def);
    }

    public short getShort(String name) {
        return (short) getInt(name);
    }

    public boolean getBoolean(String name, boolean def) {
        return CastKit.castBoolean(mDataMap.get(name), def);
    }

    public boolean getBoolean(String name) {
        return getBoolean(name, false);
    }
}
