package slieb.soy.factories.soydata;

import ch.lambdaj.function.convert.Converter;
import com.google.common.collect.ImmutableList;
import com.google.template.soy.data.SoyData;
import slieb.soy.factories.internal.AbstractConverterFactoryContext;
import slieb.soy.factories.internal.Factory;
import slieb.soy.helpers.DefaultFactoryHelper;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;

public class SoyDataConverterFactoryContext extends AbstractConverterFactoryContext<SoyData> {


    public SoyDataConverterFactoryContext(FactoryHelper factoryHelper) {
        super(factoryHelper);
    }

    public SoyDataConverterFactoryContext() {
        super(DefaultFactoryHelper.INSTANCE);
    }

    @Nonnull
    @Override
    public ImmutableList<Factory<Converter<Object, ? extends SoyData>>> getFactoriesInternal() {
        return new ImmutableList.Builder<Factory<Converter<Object, ? extends SoyData>>>()
                .add(getClassConverterFactory())
                .add(getCollectionConverterFactory())
                .add(getMapConverterFactory())
                .add(getNativeConverterFactory())
                .build();
    }

    @Nonnull
    @Override
    public SoyDataClassConverterFactory getClassConverterFactory() {
        return new SoyDataClassConverterFactory(factoryHelper, this);
    }

    @Nonnull
    @Override
    public SoyDataCollectionConverterFactory getCollectionConverterFactory() {
        return new SoyDataCollectionConverterFactory(factoryHelper, this);
    }

    @Nonnull
    @Override
    public Factory<Converter<Object, ? extends SoyData>> getMapConverterFactory() {
        return new SoyDataMapConverterFactory(factoryHelper, this);
    }

    @Nonnull
    @Override
    public Factory<Converter<Object, ? extends SoyData>> getNativeConverterFactory() {
        return new SoyDataNativeConverterFactory(factoryHelper, this);
    }

    @Override
    public void addCustomFactory(@Nonnull Factory<Converter<Object, ? extends SoyData>> factory) {
        throw new RuntimeException();
    }


}
