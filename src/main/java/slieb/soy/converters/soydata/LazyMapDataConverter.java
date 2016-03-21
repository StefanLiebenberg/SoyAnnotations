package slieb.soy.converters.soydata;

import slieb.soy.internal.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.restricted.NullData;
import slieb.soy.model.DefaultLazyResult;
import slieb.soy.model.LazySoyMapData;

import java.util.function.Function;
import java.util.function.Supplier;

public class LazyMapDataConverter implements Converter<Object, SoyData> {

    private final Function<Object, ? extends SoyMapData> soyMapDataConverter;

    public LazyMapDataConverter(Function<Object, ? extends SoyMapData> soyMapDataConverter) {
        this.soyMapDataConverter = soyMapDataConverter;
    }

    public Supplier<? extends SoyMapData> getLazyResult(Object from) {
        return new DefaultLazyResult<>(from, soyMapDataConverter);
    }

    @Override
    public SoyData apply(Object from) {
        if (from != null) {
            return new LazySoyMapData(getLazyResult(from));
        } else {
            return NullData.INSTANCE;
        }
    }
}
