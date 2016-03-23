package org.slieb.soy.converters.soydata;

import com.google.template.soy.data.SoyData;
import org.slieb.soy.context.SoyValueFactoryContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public class DynamicConverter implements Function<Object, SoyData> {

    private final SoyValueFactoryContext context;

    public DynamicConverter(@Nonnull SoyValueFactoryContext context) {
        this.context = context;
    }

    @Override
    @Nullable
    public SoyData apply(@Nullable Object from) {
        return context.apply(from);
    }
}
