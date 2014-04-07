package slieb.soy.converters;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;

import javax.annotation.Nonnull;
import java.util.Map;


public class JsonMapConverter implements Converter<Object, Map> {

    private final Converter typeConverter;

    public JsonMapConverter(@Nonnull Converter typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public Map convert(Object from) {
        if (from instanceof Map) {
            return Lambda.convertMap((Map) from, typeConverter);
        } else {
            return null;
        }
    }
}
