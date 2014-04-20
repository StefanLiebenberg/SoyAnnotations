package slieb.soy.model;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.SoyMapData;

import java.util.Iterator;
import java.util.List;


public class LazySoyListData extends SoyListData {

    private final LazyResult<SoyListData> container;

    public LazySoyListData(LazyResult<SoyListData> container) {
        this.container = container;
    }

    @Override
    public List<SoyData> asList() {
        return container.result().asList();
    }

    @Override
    public int length() {
        return container.result().length();
    }

    @Override
    public Iterator<SoyData> iterator() {
        return container.result().iterator();
    }

    @Override
    public void add(Object... values) {
        container.result().add(values);
    }

    @Override
    public void add(SoyData value) {
        container.result().add(value);
    }

    @Override
    public void add(boolean value) {
        container.result().add(value);
    }

    @Override
    public void add(int value) {
        container.result().add(value);
    }

    @Override
    public void add(double value) {
        container.result().add(value);
    }

    @Override
    public void add(String value) {
        container.result().add(value);
    }

    @Override
    public void set(int index, SoyData value) {
        container.result().set(index, value);
    }

    @Override
    public void set(int index, boolean value) {
        container.result().set(index, value);
    }

    @Override
    public void set(int index, int value) {
        container.result().set(index, value);
    }

    @Override
    public void set(int index, double value) {
        container.result().set(index, value);
    }

    @Override
    public void set(int index, String value) {
        container.result().set(index, value);
    }

    @Override
    public void remove(int index) {
        container.result().remove(index);
    }

    @Override
    public void remove(String keyStr) {
        container.result().remove(keyStr);
    }


    @Override
    public SoyData get(int index) {
        return container.result().get(index);
    }

    @Override
    public SoyData get(String keyStr) {
        return container.result().get(keyStr);
    }

    @Override
    public SoyMapData getMapData(int index) {
        return container.result().getMapData(index);
    }

    @Override
    public SoyMapData getMapData(String keyStr) {
        return container.result().getMapData(keyStr);
    }

    @Override
    public SoyListData getListData(int index) {
        return container.result().getListData(index);
    }

    @Override
    public SoyListData getListData(String keyStr) {
        return container.result().getListData(keyStr);
    }

    @Override
    public boolean getBoolean(int index) {
        return container.result().getBoolean(index);
    }

    @Override
    public boolean getBoolean(String keyStr) {
        return container.result().getBoolean(keyStr);
    }

    @Override
    public int getInteger(int index) {
        return container.result().getInteger(index);
    }

    @Override
    public int getInteger(String keyStr) {
        return container.result().getInteger(keyStr);
    }

    @Override
    public double getFloat(int index) {
        return container.result().getFloat(index);
    }

    @Override
    public double getFloat(String keyStr) {
        return container.result().getFloat(keyStr);
    }

    @Override
    public String getString(int index) {
        return container.result().getString(index);
    }

    @Override
    public String getString(String keyStr) {
        return container.result().getString(keyStr);
    }

    @Override
    public void putSingle(String key, SoyData value) {
        container.result().putSingle(key, value);
    }

    @Override
    public void removeSingle(String key) {
        container.result().removeSingle(key);
    }

    @Override
    public SoyData getSingle(String key) {
        return container.result().getSingle(key);
    }


    @Override
    public boolean toBoolean() {
        return container.result().toBoolean();
    }

    @Override
    public boolean equals(Object other) {
        return container.result().equals(other);
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
    public String toString() {
        return container.toString();
    }
}
