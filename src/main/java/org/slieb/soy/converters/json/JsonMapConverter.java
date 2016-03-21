package org.slieb.soy.converters.json;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.function.Function;

import static ch.lambdaj.Lambda.convertMap;

public class JsonMapConverter implements Function<Object, Map> {

    private final Function<Object, ?> typeConverter;

    public JsonMapConverter(@Nonnull Function<Object, ?> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public Map apply(Object from) {
        if (from instanceof Map<?, ?>) {
            return convertMap((Map<?, ?>) from, typeConverter);
        } else {
            return null;
        }
    }
}
