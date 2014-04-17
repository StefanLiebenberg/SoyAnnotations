package slieb.soy.converters.json;


import ch.lambdaj.function.convert.Converter;
import slieb.soy.context.JsonDataFactoryContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DynamicConverter implements Converter<Object, Object> {

    private final JsonDataFactoryContext context;

    public DynamicConverter(@Nonnull JsonDataFactoryContext context) {
        this.context = context;
    }

    @Override
    @Nullable
    public Object convert(@Nullable Object from) {
        return context.convert(from);
    }
}
