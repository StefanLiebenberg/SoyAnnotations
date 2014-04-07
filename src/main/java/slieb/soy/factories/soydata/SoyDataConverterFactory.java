package slieb.soy.factories.soydata;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import slieb.soy.factories.internal.AbstractConverterFactory;
import slieb.soy.factories.internal.ConverterFactoryContext;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;

public class SoyDataConverterFactory extends AbstractConverterFactory<SoyData> {

    public SoyDataConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<SoyData> factoryContext) {
        super(factoryHelper, factoryContext);
    }


    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Boolean.FALSE;
    }

    @Nonnull
    @Override
    public Converter<Object, ? extends SoyData> create(@Nonnull Class<?> classObject) {
        return null;
    }
}
