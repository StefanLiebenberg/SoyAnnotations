package org.slieb.soy.converters.json;

import org.slieb.soy.context.JsonDataFactoryContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public class DynamicConverter implements Function<Object, Object> {

    private final JsonDataFactoryContext context;

    public DynamicConverter(@Nonnull JsonDataFactoryContext context) {
        this.context = context;
    }

    @Override
    @Nullable
    public Object apply(@Nullable Object from) {
        return context.apply(from);
    }
}
