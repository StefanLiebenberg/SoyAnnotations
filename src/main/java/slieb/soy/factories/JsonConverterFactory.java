package slieb.soy.factories;

import slieb.soy.context.JsonDataFactoryContext;

import javax.annotation.Nonnull;
import java.util.function.Function;

public interface JsonConverterFactory {

    @Nonnull
    Boolean canCreate(@Nonnull Class<?> classObject);

    @Nonnull
    Function<Object, ?> create(@Nonnull Class<?> classObject,
                               @Nonnull JsonDataFactoryContext context);
}
