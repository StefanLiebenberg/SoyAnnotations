package slieb.soy.model;


import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.SoyMapData;

import java.util.Map;
import java.util.Set;

public class LazySoyMapData extends SoyMapData {

    private final LazyResult<? extends SoyMapData> container;

    public LazySoyMapData(LazyResult<? extends SoyMapData> container) {
        this.container = container;
    }

    @Override
    public Map<String, SoyData> asMap() {
        return container.result().asMap();
    }

    @Override
    public Set<String> getKeys() {
        return container.result().getKeys();
    }

    @Override
    public String toString() {
        return "[LAZY-MAP:CANNOT-CONSTRUCT]";
//        return container.result().toString();
    }

    @Override
    public boolean equals(Object other) {
        return container.result().equals(other);
    }

    @Override
    public void putSingle(String key, SoyData value) {
        container.result().putSingle(key, value);
    }

    @Override
    public SoyData getSingle(String key) {
        return container.result().getSingle(key);
    }

    @Override
    public void removeSingle(String key) {
        container.result().removeSingle(key);
    }


    @Override
    public void put(Object... data) {
        container.result().put(data);
    }

    @Override
    public void put(String keyStr, SoyData value) {
        container.result().put(keyStr, value);
    }

    @Override
    public void put(String keyStr, boolean value) {
        container.result().put(keyStr, value);
    }

    @Override
    public void put(String keyStr, int value) {
        container.result().put(keyStr, value);
    }

    @Override
    public void put(String keyStr, double value) {
        container.result().put(keyStr, value);
    }

    @Override
    public void put(String keyStr, String value) {
        container.result().put(keyStr, value);
    }

    @Override
    public void remove(String keyStr) {
        container.result().remove(keyStr);
    }

    @Override
    public SoyData get(String keyStr) {
        return container.result().get(keyStr);
    }

    @Override
    public SoyMapData getMapData(String keyStr) {
        return container.result().getMapData(keyStr);
    }

    @Override
    public SoyListData getListData(String keyStr) {
        return container.result().getListData(keyStr);
    }

    @Override
    public boolean getBoolean(String keyStr) {
        return container.result().getBoolean(keyStr);
    }

    @Override
    public int getInteger(String keyStr) {
        return container.result().getInteger(keyStr);
    }

    @Override
    public double getFloat(String keyStr) {
        return container.result().getFloat(keyStr);
    }

    @Override
    public String getString(String keyStr) {
        return container.result().getString(keyStr);
    }

    @Override
    public boolean toBoolean() {
        return container.result().toBoolean();
    }

    @Override
    public boolean booleanValue() {
        return container.result().booleanValue();
    }

    @Override
    public int integerValue() {
        return container.result().integerValue();
    }

    @Override
    public double floatValue() {
        return container.result().floatValue();
    }

    @Override
    public double numberValue() {
        return container.result().numberValue();
    }

    @Override
    public String stringValue() {
        return container.result().stringValue();
    }
}
