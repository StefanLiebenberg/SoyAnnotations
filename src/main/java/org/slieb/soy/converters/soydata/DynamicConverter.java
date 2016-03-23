package org.slieb.soy.converters.soydata;

import com.google.template.soy.data.SoyValue;
import org.slieb.soy.context.SoyValueFactoryContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public class DynamicConverter implements Function<Object, SoyValue> {

    private final SoyValueFactoryContext context;

    public DynamicConverter(@Nonnull SoyValueFactoryContext context) {
        this.context = context;
    }

    @Override
    @Nullable
    public SoyValue apply(@Nullable Object from) {
        return context.apply(from);
    }
}
