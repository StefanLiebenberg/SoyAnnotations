package slieb.soy.factories.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import slieb.soy.converters.SoyListDataConverter;
import slieb.soy.factories.internal.AbstractConverterFactory;
import slieb.soy.factories.internal.ConverterFactoryContext;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;
import java.util.Collection;

import static slieb.soy.converters.NullSafeConverter.wrapConverterWithNullSafe;

public class SoyDataCollectionConverterFactory extends AbstractConverterFactory<SoyData> {

    public SoyDataCollectionConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<SoyData> factoryContext) {
        super(factoryHelper, factoryContext);
    }

    @Nonnull
    @Override
    public Converter<Object, ? extends SoyData> create(@Nonnull Class<?> classObject) {
        return new SoyListDataConverter(wrapConverterWithNullSafe(getDynamicConverter()));
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Collection.class.isAssignableFrom(classObject);
    }
}
