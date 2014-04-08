package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.restricted.NullData;
import slieb.soy.model.DefaultDynamicResult;
import slieb.soy.model.DynamicSoyMapData;

import javax.annotation.Nonnull;
import java.util.Map;

public class DynamicMapConverter implements Converter<Object, SoyData> {

    private final Converter<Object, SoyMapData> soyMapDataConverter;

    public DynamicMapConverter(@Nonnull Converter<Object, SoyMapData> soyMapDataConverter) {
        this.soyMapDataConverter = soyMapDataConverter;
    }

    @Override
    public SoyData convert(Object from) {
        if (from instanceof Map) {
            return new DynamicSoyMapData(new DefaultDynamicResult<>(from, soyMapDataConverter));
        } else {
            return NullData.INSTANCE;
        }
    }
}
