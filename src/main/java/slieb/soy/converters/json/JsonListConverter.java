package slieb.soy.converters.json;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;


public class JsonListConverter implements Converter<Object, List> {

    private final Converter<Object, ?> typeConverter;

    public JsonListConverter(@Nonnull Converter<Object, ?> typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public List convert(Object from) {
        if (from instanceof Collection) {
            return Lambda.convert(from, typeConverter);
        } else {
            return null;
        }
    }
}
