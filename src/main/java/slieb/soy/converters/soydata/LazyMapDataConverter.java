package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.restricted.NullData;
import slieb.soy.model.DefaultLazyResult;
import slieb.soy.model.LazySoyMapData;

import javax.annotation.Nonnull;
import java.util.Map;

public class LazyMapDataConverter implements Converter<Object, SoyData> {

    private final Converter<Object, ? extends SoyMapData> soyMapDataConverter;

    public LazyMapDataConverter(@Nonnull Converter<Object, ? extends SoyMapData> soyMapDataConverter) {
        this.soyMapDataConverter = soyMapDataConverter;
    }

    @Override
    public SoyData convert(Object from) {
        if (from instanceof Map) {
            return new LazySoyMapData(new DefaultLazyResult<>(from, soyMapDataConverter));
        } else {
            return NullData.INSTANCE;
        }
    }
}
