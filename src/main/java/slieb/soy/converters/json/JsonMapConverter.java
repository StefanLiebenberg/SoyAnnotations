package slieb.soy.converters.json;

import ch.lambdaj.function.convert.Converter;

import javax.annotation.Nonnull;
import java.util.Map;

import static ch.lambdaj.Lambda.convertMap;


public class JsonMapConverter implements Converter<Object, Map> {

    private final Converter<Object, ?> typeConverter;

    public JsonMapConverter(@Nonnull Converter<Object, ?> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public Map convert(Object from) {
        if (from instanceof Map<?, ?>) {
            return convertMap((Map<?, ?>) from, typeConverter);
        } else {
            return null;
        }
    }
}
