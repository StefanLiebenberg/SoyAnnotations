package slieb.soy.meta;


import com.google.inject.Inject;
import slieb.soy.converters.meta.MetaClassConverter;
import slieb.soy.factories.MetaConverterFactory;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;

public class MetaConverterClassConverter implements MetaConverterFactory {

    private final FactoryHelper factoryHelper;

    @Inject
    public MetaConverterClassConverter(FactoryHelper factoryHelper) {
        this.factoryHelper = factoryHelper;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return factoryHelper.isFactoryClass(classObject);
    }

    @Nonnull
    @Override
    public MetaClassConverter create(@Nonnull Class<?> classObject) {
        return new MetaClassConverter(factoryHelper);
    }
}
