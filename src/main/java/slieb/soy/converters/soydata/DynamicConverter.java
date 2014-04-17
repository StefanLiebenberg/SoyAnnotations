package slieb.soy.converters.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import slieb.soy.context.SoyDataFactoryContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DynamicConverter implements Converter<Object, SoyData> {

    private final SoyDataFactoryContext context;

    public DynamicConverter(@Nonnull SoyDataFactoryContext context) {
        this.context = context;
    }

    @Override
    @Nullable
    public SoyData convert(@Nullable Object from) {
        return context.convert(from);
    }
}
