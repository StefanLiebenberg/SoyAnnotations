package slieb.soy.factories;


import slieb.soy.internal.Converter;
import slieb.soy.meta.MetaClassInformation;

import javax.annotation.Nonnull;

public interface MetaConverterFactory {

    @Nonnull
    public Boolean canCreate(Class<?> classObject);

    @Nonnull
    public Converter<Class<?>, MetaClassInformation> create(@Nonnull Class<?> classObject);
}
