package slieb.soy.factories;


import ch.lambdaj.function.convert.Converter;
import slieb.soy.context.JsonDataFactoryContext;

import javax.annotation.Nonnull;

public interface JsonConverterFactory {

    @Nonnull
    public Boolean canCreate(@Nonnull Class<?> classObject);

    @Nonnull
    public Converter<Object, ?> create(@Nonnull Class<?> classObject, @Nonnull JsonDataFactoryContext context);
}
