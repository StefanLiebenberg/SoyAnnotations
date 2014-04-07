package slieb.soy.factories.internal;


import ch.lambdaj.function.convert.Converter;
import slieb.soy.converters.DynamicConverter;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;

public abstract class AbstractConverterFactory<A> implements Factory<Converter<Object, ? extends A>> {

    protected final FactoryHelper factoryHelper;

    protected final ConverterFactoryContext<A> factoryContext;

    protected AbstractConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<A> factoryContext) {
        this.factoryHelper = factoryHelper;
        this.factoryContext = factoryContext;
    }

    protected Converter<Object, ? extends A> getTypeConverter(Class<?> classType) {
        return factoryContext.create(classType);
    }

    protected Converter<Object, ? extends A> getDynamicConverter() {
        return new DynamicConverter<>(factoryContext);
    }

    @Nonnull
    @Override
    public abstract Converter<Object, ? extends A> create(@Nonnull Class<?> classObject);

    @Nonnull
    @Override
    public abstract Boolean canCreate(@Nonnull Class<?> classObject);

}
