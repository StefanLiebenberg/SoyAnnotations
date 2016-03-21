package org.slieb.soy.meta;


import org.slieb.soy.internal.Converter;
import com.google.inject.Inject;
import org.slieb.soy.annotations.CustomConverter;
import org.slieb.soy.factories.MetaConverterFactory;
import org.slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;

public class MetaCustomClassConverterFactory implements Converter<Class<?>, MetaClassInformation>,
        MetaConverterFactory {

    private final FactoryHelper factoryHelper;

    @Inject
    public MetaCustomClassConverterFactory(FactoryHelper factoryHelper) {
        this.factoryHelper = factoryHelper;
    }

    @Nonnull
    public MetaConverter getConverterInstance(Class<? extends MetaConverter> converterClass) {
        try {
            MetaConverter converter = converterClass.newInstance();
            if (converter instanceof MetaFactoryHelperAware) {
                ((MetaFactoryHelperAware) converter).setFactoryHelper(factoryHelper);
            }
            return converter;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    public Class<? extends MetaConverter> getConverterClass(Class<?> classObject) {
        return classObject.getAnnotation(CustomConverter.class).value();
    }

    public MetaConverter getConverter(Class<?> classObject) {
        return getConverterInstance(getConverterClass(classObject));
    }

    @Override
    public MetaClassInformation apply(Class<?> from) {
        return new MetaClassInformation(Boolean.TRUE, from, getConverter(from), null, false);
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return classObject.isAnnotationPresent(CustomConverter.class);
    }

    @Nonnull
    @Override
    public MetaCustomClassConverterFactory create(@Nonnull Class<?> classObject) {
        return this;
    }
}
