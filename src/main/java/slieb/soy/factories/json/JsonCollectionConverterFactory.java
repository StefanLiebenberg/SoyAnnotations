package slieb.soy.factories.json;


import ch.lambdaj.function.convert.Converter;
import slieb.soy.converters.JsonListConverter;
import slieb.soy.factories.internal.AbstractConverterFactory;
import slieb.soy.factories.internal.ConverterFactoryContext;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class JsonCollectionConverterFactory extends AbstractConverterFactory<Object> {

    public JsonCollectionConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<Object> factoryContext) {
        super(factoryHelper, factoryContext);
    }

    @Nonnull
    @Override
    public Converter<Object, ? extends List> create(@Nonnull Class<?> classObject) {
        checkArgument(canCreate(classObject));
        return new JsonListConverter(getDynamicConverter());
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Collection.class.isAssignableFrom(classObject);
    }
}
