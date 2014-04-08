package slieb.soy.factories.soydata;

import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import slieb.soy.converters.soydata.SoyDataMapConverter;
import slieb.soy.factories.internal.AbstractConverterFactory;
import slieb.soy.factories.internal.ConverterFactoryContext;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;
import java.util.Map;

import static slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;


public class SoyDataMapConverterFactory extends AbstractConverterFactory<SoyData> {

    public SoyDataMapConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<SoyData> factoryContext) {
        super(factoryHelper, factoryContext);
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Map.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Converter<Object, ? extends SoyMapData> create(@Nonnull Class<?> classObject) {
        return new SoyDataMapConverter(wrapConverterWithNullSafe(getDynamicConverter()));
    }
}
