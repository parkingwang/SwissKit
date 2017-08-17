package com.parkingwang.lang.data;

import com.parkingwang.lang.kit.CastKit;
import com.parkingwang.lang.kit.MapReader;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
public class KeyMap implements Map<String, Object> {

    public static final KeyMap EMPTY = new KeyMap(Collections.EMPTY_MAP);

    private final Map<String, Object> mRealMap;

    public KeyMap(Map<String, Object> realMap) {
        mRealMap = realMap;
    }

    public KeyMap() {
        this(new HashMap<String, Object>());
    }

    // Factory

    public static KeyMap newHashMap(){
        return new KeyMap();
    }

    public static KeyMap newConcurrentHashMap(){
        return new KeyMap(new ConcurrentHashMap<String, Object>());
    }

    public static KeyMap newLinkedHashMap(){
        return new KeyMap(new LinkedHashMap<String, Object>());
    }

    // Overrides

    @Override
    public int size() {
        return mRealMap.size();
    }

    @Override
    public boolean isEmpty() {
        return mRealMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return mRealMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return mRealMap.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return mRealMap.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return mRealMap.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return mRealMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        mRealMap.putAll(m);
    }

    @Override
    public void clear() {
        mRealMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return mRealMap.keySet();
    }

    @Override
    public Collection<Object> values() {
        return mRealMap.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return mRealMap.entrySet();
    }

    // Extensions

    public KeyMap putIfNotNull(String key, Object value){
        if (value != null) {
            put(key, value);
        }
        return this;
    }

    public KeyMap putIfNotEmpty(String key, CharSequence value){
        if (value != null && value.length() > 0) {
            put(key, value);
        }
        return this;
    }

    public <T> T getCast(String key, T defaultValue){
        final Object value = get(key);
        if (value == null){
            return defaultValue;
        }else {
            return (T) value;
        }
    }

    public int getIntValue(String key) {
        return getInt(key, 0);
    }

    public long getLongValue(String key) {
        return getLong(key, 0L);
    }

    public float getFloatValue(String key) {
        return getFloat(key, 0f);
    }

    public double getDoubleValue(String key) {
        return getDouble(key, 0.0);
    }

    public boolean getBooleanValue(String key) {
        return getBoolean(key, false);
    }

    public String getStringValue(String key) {
        return getString(key, "");
    }

    public Integer getInt(String key, Integer defaultValue) {
        final Object value = get(key);
        return CastKit.castInt(value, defaultValue);
    }

    public Long getLong(String key, Long defaultValue) {
        final Object value = get(key);
        return CastKit.castLong(value, defaultValue);
    }

    public Double getDouble(String key, Double defaultValue) {
        final Object value = get(key);
        return CastKit.castDouble(value, defaultValue);
    }

    public Float getFloat(String key, Float defaultValue) {
        final Object value = get(key);
        return CastKit.castFloat(value, defaultValue);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        final Object value = get(key);
        return CastKit.castBoolean(value, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        final Object value = get(key);
        return CastKit.castString(value, defaultValue);
    }

    // Toolkit

    public MapReader getReader(){
        return MapReader.of(mRealMap);
    }

    @Override
    public String toString() {
        return mRealMap.toString();
    }
}
