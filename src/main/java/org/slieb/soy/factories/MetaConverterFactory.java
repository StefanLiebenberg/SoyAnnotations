package org.slieb.soy.factories;


import org.slieb.soy.internal.Converter;
import org.slieb.soy.meta.MetaClassInformation;

import javax.annotation.Nonnull;

public interface MetaConverterFactory {

    @Nonnull
    public Boolean canCreate(Class<?> classObject);

    @Nonnull
    public Converter<Class<?>, MetaClassInformation> create(@Nonnull Class<?> classObject);
}
