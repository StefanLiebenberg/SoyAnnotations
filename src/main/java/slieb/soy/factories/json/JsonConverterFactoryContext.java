package slieb.soy.factories.json;


import ch.lambdaj.function.convert.Converter;
import slieb.soy.factories.internal.AbstractConverterFactoryContext;
import slieb.soy.factories.internal.Factory;
import slieb.soy.helpers.DefaultFactoryHelper;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;

public class JsonConverterFactoryContext extends AbstractConverterFactoryContext<Object> {


    public JsonConverterFactoryContext(FactoryHelper factoryHelper) {
        super(factoryHelper);
    }

    public JsonConverterFactoryContext() {
        super(new DefaultFactoryHelper());
    }

    @Nonnull
    @Override
    public Factory<Converter<Object, ?>> getClassConverterFactory() {
        return new JsonClassConverterFactory(factoryHelper, this);
    }


    @Nonnull
    @Override
    public Factory<Converter<Object, ?>> getCollectionConverterFactory() {
        return new JsonCollectionConverterFactory(factoryHelper, this);
    }


    @Nonnull
    @Override
    public Factory<Converter<Object, ?>> getMapConverterFactory() {
        return new JsonMapConverterFactory(factoryHelper, this);
    }


    @Nonnull
    @Override
    public Factory<Converter<Object, ?>> getNativeConverterFactory() {
        return new JsonNativeConverterFactory(factoryHelper, this);
    }


}
