package slieb.soy.factories.json;


import ch.lambdaj.function.convert.Converter;
import com.google.common.primitives.Primitives;
import slieb.soy.converters.json.JsonIdentityConverter;
import slieb.soy.factories.internal.AbstractConverterFactory;
import slieb.soy.factories.internal.ConverterFactoryContext;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;

public class JsonNativeConverterFactory extends AbstractConverterFactory<Object> {

    public JsonNativeConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<Object> factoryContext) {
        super(factoryHelper, factoryContext);
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Primitives.isWrapperType(classObject) || classObject.isPrimitive() ||
                String.class.equals(classObject) || Object.class.equals(classObject);
    }

    @Nonnull
    @Override
    public Converter<Object, ?> create(@Nonnull Class<?> classObject) {
        return JsonIdentityConverter.INSTANCE;
    }
}
