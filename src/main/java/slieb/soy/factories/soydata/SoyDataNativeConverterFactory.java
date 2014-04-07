package slieb.soy.factories.soydata;

import ch.lambdaj.function.convert.Converter;
import com.google.common.primitives.Primitives;
import com.google.template.soy.data.SoyData;
import slieb.soy.converters.LongConverter;
import slieb.soy.converters.ObjectConverter;
import slieb.soy.factories.internal.AbstractConverterFactory;
import slieb.soy.factories.internal.ConverterFactoryContext;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;


public class SoyDataNativeConverterFactory extends AbstractConverterFactory<SoyData> {

    public SoyDataNativeConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<SoyData> factoryContext) {
        super(factoryHelper, factoryContext);
    }

    @Nonnull
    @Override
    public Converter<Object, ? extends SoyData> create(@Nonnull Class<?> classObject) {
        if(Long.class.isAssignableFrom(classObject)) {
            return LongConverter.INSTANCE;
        }
        return ObjectConverter.INSTANCE;
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Primitives.isWrapperType(classObject) || classObject.isPrimitive() ||
                String.class.equals(classObject) || Object.class.equals(classObject);
    }
}
