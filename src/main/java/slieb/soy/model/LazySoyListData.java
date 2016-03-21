package slieb.soy.model;

import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyList;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.SoyValueProvider;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public class LazySoyListData
        extends SoyData
        implements SoyList {

    private final Supplier<SoyList> container;

    public LazySoyListData(Supplier<SoyList> container) {
        this.container = container;
    }

    @Override
    public boolean toBoolean() {
        return container.get().booleanValue();
    }

    @Override
    public String toString() {
        return container.get().toString();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(final Object other) {
        return container.get().equals(other);
    }

    @Override
    public int length() {
        return 0;
    }

    @Nonnull
    @Override
    public List<? extends SoyValueProvider> asJavaList() {
        return container.get().asJavaList();
    }

    @Nonnull
    @Override
    public List<? extends SoyValue> asResolvedJavaList() {
        return container.get().asResolvedJavaList();
    }

    @Override
    public SoyValue get(final int index) {
        return null;
    }

    @Override
    public SoyValueProvider getProvider(final int index) {
        return container.get().getProvider(index);
    }

    @Override
    public int getItemCnt() {
        return container.get().getItemCnt();
    }

    @Nonnull
    @Override
    public Iterable<? extends SoyValue> getItemKeys() {
        return container.get().getItemKeys();
    }

    @Override
    public boolean hasItem(final SoyValue key) {
        return container.get().hasItem(key);
    }

    @Override
    public SoyValue getItem(final SoyValue key) {
        return container.get().getItem(key);
    }

    @Override
    public SoyValueProvider getItemProvider(final SoyValue key) {
        return container.get().getItemProvider(key);
    }

    @Override
    public void render(final Appendable appendable) throws IOException {

        this.container.get().render(appendable);
    }

    //
    //    @Override
    //    public int length() {
    //        return container.get().length();
    //    }
    //
    //    @Override
    //    public Iterator<SoyData> iterator() {
    //        return container.get().iterator();
    //    }
    //
    //    @Override
    //    public void add(Object... values) {
    //        container.get().add(values);
    //    }
    //
    //    @Override
    //    public void add(SoyData value) {
    //        container.get().add(value);
    //    }
    //
    //    @Override
    //    public void add(boolean value) {
    //        container.get().add(value);
    //    }
    //
    //    @Override
    //    public void add(int value) {
    //        container.get().add(value);
    //    }
    //
    //    @Override
    //    public void add(double value) {
    //        container.get().add(value);
    //    }
    //
    //    @Override
    //    public void add(String value) {
    //        container.get().add(value);
    //    }
    //
    //    @Override
    //    public void set(int index,
    //                    SoyData value) {
    //        container.get().set(index, value);
    //    }
    //
    //    @Override
    //    public void set(int index,
    //                    boolean value) {
    //        container.get().set(index, value);
    //    }
    //
    //    @Override
    //    public void set(int index,
    //                    int value) {
    //        container.get().set(index, value);
    //    }
    //
    //    @Override
    //    public void set(int index,
    //                    double value) {
    //        container.get().set(index, value);
    //    }
    //
    //    @Override
    //    public void set(int index,
    //                    String value) {
    //        container.get().set(index, value);
    //    }
    //
    //    @Override
    //    public void remove(int index) {
    //        container.get().remove(index);
    //    }
    //
    //    @Override
    //    public void remove(String keyStr) {
    //        container.get().remove(keyStr);
    //    }
    //
    //    @Override
    //    public SoyData get(int index) {
    //        return container.get().get(index);
    //    }
    //
    //    @Override
    //    public SoyData get(String keyStr) {
    //        return container.get().get(keyStr);
    //    }
    //
    //    @Override
    //    public SoyMapData getMapData(int index) {
    //        return container.get().getMapData(index);
    //    }
    //
    //    @Override
    //    public SoyMapData getMapData(String keyStr) {
    //        return container.get().getMapData(keyStr);
    //    }
    //
    //    @Override
    //    public SoyListData getListData(int index) {
    //        return container.get().getListData(index);
    //    }
    //
    //    @Override
    //    public SoyListData getListData(String keyStr) {
    //        return container.get().getListData(keyStr);
    //    }
    //
    //    @Override
    //    public boolean getBoolean(int index) {
    //        return container.get().getBoolean(index);
    //    }
    //
    //    @Override
    //    public boolean getBoolean(String keyStr) {
    //        return container.get().getBoolean(keyStr);
    //    }
    //
    //    @Override
    //    public int getInteger(int index) {
    //        return container.get().getInteger(index);
    //    }
    //
    //    @Override
    //    public int getInteger(String keyStr) {
    //        return container.get().getInteger(keyStr);
    //    }
    //
    //    @Override
    //    public double getFloat(int index) {
    //        return container.get().getFloat(index);
    //    }
    //
    //    @Override
    //    public double getFloat(String keyStr) {
    //        return container.get().getFloat(keyStr);
    //    }
    //
    //    @Override
    //    public String getString(int index) {
    //        return container.get().getString(index);
    //    }
    //
    //    @Override
    //    public String getString(String keyStr) {
    //        return container.get().getString(keyStr);
    //    }
    //
    //    @Override
    //    public void putSingle(String key,
    //                          SoyData value) {
    //        container.get().putSingle(key, value);
    //    }
    //
    //    @Override
    //    public void removeSingle(String key) {
    //        container.get().removeSingle(key);
    //    }
    //
    //    @Override
    //    public SoyData getSingle(String key) {
    //        return container.get().getSingle(key);
    //    }
    //
    //    @Override
    //    public boolean toBoolean() {
    //        return container.get().toBoolean();
    //    }
    //
    //    @Override
    //    public boolean equals(Object other) {
    //        return container.get().equals(other);
    //    }
    //
    //    @Override
    //    public boolean booleanValue() {
    //        return container.get().booleanValue();
    //    }
    //
    //    @Override
    //    public int integerValue() {
    //        return container.get().integerValue();
    //    }
    //
    //    @Override
    //    public double floatValue() {
    //        return container.get().floatValue();
    //    }
    //
    //    @Override
    //    public double numberValue() {
    //        return container.get().numberValue();
    //    }
    //
    //    @Override
    //    public String stringValue() {
    //        return container.get().stringValue();
    //    }
    //
    //    @Override
    //    public void put(Object... data) {
    //        container.get().put(data);
    //    }
    //
    //    @Override
    //    public void put(String keyStr,
    //                    SoyData value) {
    //        container.get().put(keyStr, value);
    //    }
    //
    //    @Override
    //    public void put(String keyStr,
    //                    boolean value) {
    //        container.get().put(keyStr, value);
    //    }
    //
    //    @Override
    //    public void put(String keyStr,
    //                    int value) {
    //        container.get().put(keyStr, value);
    //    }
    //
    //    @Override
    //    public void put(String keyStr,
    //                    double value) {
    //        container.get().put(keyStr, value);
    //    }
    //
    //    @Override
    //    public void put(String keyStr,
    //                    String value) {
    //        container.get().put(keyStr, value);
    //    }
    //
    //    @Override
    //    public String toString() {
    //        return container.toString();
    //    }
}
