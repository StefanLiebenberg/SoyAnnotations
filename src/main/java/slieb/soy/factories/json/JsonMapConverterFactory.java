package slieb.soy.factories.json;


import ch.lambdaj.function.convert.Converter;
import slieb.soy.converters.json.JsonMapConverter;
import slieb.soy.factories.internal.AbstractConverterFactory;
import slieb.soy.factories.internal.ConverterFactoryContext;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class JsonMapConverterFactory extends AbstractConverterFactory<Object> {

    public JsonMapConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<Object> factoryContext) {
        super(factoryHelper, factoryContext);
    }

    @Nonnull
    @Override
    public Converter<Object, ? extends Map> create(@Nonnull Class<?> classObject) {
        checkArgument(canCreate(classObject));
        return new JsonMapConverter(getDynamicConverter());
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Map.class.isAssignableFrom(classObject);
    }
}
