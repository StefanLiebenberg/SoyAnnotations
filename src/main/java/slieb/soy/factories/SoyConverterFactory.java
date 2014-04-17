package slieb.soy.factories;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import slieb.soy.context.SoyDataFactoryContext;

import javax.annotation.Nonnull;

public interface SoyConverterFactory {

    @Nonnull
    public Boolean canCreate(@Nonnull Class<?> classObject);

    @Nonnull
    public Converter<Object, ? extends SoyData> create(@Nonnull Class<?> classObject, @Nonnull SoyDataFactoryContext context);
}
