package slieb.soy.converters;


import ch.lambdaj.function.convert.Converter;
import com.google.common.collect.ImmutableMap;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import javax.annotation.Nonnull;
import java.util.Map;

public class StrictClassToSoyMapDataConverter implements Converter<Object, SoyMapData> {

    private final ImmutableMap<String, Converter<Object, ? extends SoyData>> memberConverters;

    public StrictClassToSoyMapDataConverter(ImmutableMap<String, Converter<Object, ? extends SoyData>> memberConverters) {
        this.memberConverters = memberConverters;
    }

    @Override
    @Nonnull
    public SoyMapData convert(@Nonnull Object from) {
        SoyMapData soyMapData = new SoyMapData();
        for (Map.Entry<String, Converter<Object, ? extends SoyData>> entry : memberConverters.entrySet()) {
            soyMapData.putSingle(entry.getKey(), entry.getValue().convert(from));
        }
        return soyMapData;
    }
}
