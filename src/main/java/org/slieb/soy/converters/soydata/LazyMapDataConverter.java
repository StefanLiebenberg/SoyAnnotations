package org.slieb.soy.converters.soydata;

import com.google.template.soy.data.SoyMap;
import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.restricted.NullData;
import org.slieb.soy.model.DefaultLazyResult;
import org.slieb.soy.model.LazySoyMapData;

import java.util.function.Function;
import java.util.function.Supplier;

public class LazyMapDataConverter implements Function<Object, SoyValue> {

    private final Function<Object, SoyMap> soyMapDataConverter;

    public LazyMapDataConverter(Function<Object, SoyMap> soyMapDataConverter) {
        this.soyMapDataConverter = soyMapDataConverter;
    }

    public Supplier<SoyMap> getLazyResult(Object from) {
        return new DefaultLazyResult<>(from, soyMapDataConverter);
    }

    @Override
    public SoyValue apply(Object from) {
        if (from != null) {
            return new LazySoyMapData(getLazyResult(from));
        } else {
            return NullData.INSTANCE;
        }
    }
}
