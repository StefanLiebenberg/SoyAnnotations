package slieb.soy.converters;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;


public class JsonListConverter implements Converter<Object, List> {

    private final Converter typeConverter;

    public JsonListConverter(@Nonnull Converter typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public List convert(Object from) {
        if (from != null && from instanceof Collection) {
            return Lambda.convert(from, typeConverter);
        } else {
            return null;
        }
    }
}
