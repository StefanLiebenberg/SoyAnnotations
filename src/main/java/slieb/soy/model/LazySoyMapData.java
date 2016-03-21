package slieb.soy.model;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.SoyMapData;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class LazySoyMapData extends SoyMapData {

    private final Supplier<? extends SoyMapData> container;

    public LazySoyMapData(Supplier<? extends SoyMapData> container) {
        this.container = container;
    }

    @Override
    public Map<String, SoyData> asMap() {
        return container.get().asMap();
    }

    @Override
    public Set<String> getKeys() {
        return container.get().getKeys();
    }

    @Override
    public boolean equals(Object other) {
        return container.get().equals(other);
    }

    @Override
    public void putSingle(String key,
                          SoyData value) {
        container.get().putSingle(key, value);
    }

    @Override
    public SoyData getSingle(String key) {
        return container.get().getSingle(key);
    }

    @Override
    public void removeSingle(String key) {
        container.get().removeSingle(key);
    }

    @Override
    public void put(Object... data) {
        container.get().put(data);
    }

    @Override
    public void put(String keyStr,
                    SoyData value) {
        container.get().put(keyStr, value);
    }

    @Override
    public void put(String keyStr,
                    boolean value) {
        container.get().put(keyStr, value);
    }

    @Override
    public void put(String keyStr,
                    int value) {
        container.get().put(keyStr, value);
    }

    @Override
    public void put(String keyStr,
                    double value) {
        container.get().put(keyStr, value);
    }

    @Override
    public void put(String keyStr,
                    String value) {
        container.get().put(keyStr, value);
    }

    @Override
    public void remove(String keyStr) {
        container.get().remove(keyStr);
    }

    @Override
    public SoyData get(String keyStr) {
        return container.get().get(keyStr);
    }

    @Override
    public SoyMapData getMapData(String keyStr) {
        return container.get().getMapData(keyStr);
    }

    @Override
    public SoyListData getListData(String keyStr) {
        return container.get().getListData(keyStr);
    }

    @Override
    public boolean getBoolean(String keyStr) {
        return container.get().getBoolean(keyStr);
    }

    @Override
    public int getInteger(String keyStr) {
        return container.get().getInteger(keyStr);
    }

    @Override
    public double getFloat(String keyStr) {
        return container.get().getFloat(keyStr);
    }

    @Override
    public String getString(String keyStr) {
        return container.get().getString(keyStr);
    }

    @Override
    public boolean toBoolean() {
        return container.get().toBoolean();
    }

    @Override
    public boolean booleanValue() {
        return container.get().booleanValue();
    }

    @Override
    public int integerValue() {
        return container.get().integerValue();
    }

    @Override
    public double floatValue() {
        return container.get().floatValue();
    }

    @Override
    public double numberValue() {
        return container.get().numberValue();
    }

    @Override
    public String stringValue() {
        return container.get().stringValue();
    }

    @Override
    public String toString() {
        return container.toString();
    }
}
