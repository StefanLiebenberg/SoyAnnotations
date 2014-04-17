package slieb.soy.converters.soydata;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;

import java.util.Map;


public class SoyMapDataConverter implements Converter<Object, SoyMapData> {

    private final Converter<Object, SoyData> typeConverter;

    public SoyMapDataConverter(Converter<Object, SoyData> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public SoyMapData convert(Object from) {
        if (from instanceof Map<?, ?>) {
            @SuppressWarnings("unchecked")
            Map<String, ?> castMap = (Map<String, ?>) from;
            SoyMapData soyMapData = new SoyMapData();
            for (Map.Entry<String, ?> entry : castMap.entrySet()) {
                soyMapData.put(entry.getKey(), typeConverter.convert(entry.getValue()));
            }
            return soyMapData;
        } else {
            return null;
        }
    }
}
