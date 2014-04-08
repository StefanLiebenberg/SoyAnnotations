package slieb.soy.factories.internal;


import ch.lambdaj.function.convert.Converter;
import com.google.common.collect.ImmutableList;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;

public abstract class AbstractConverterFactoryContext<A>
        extends AbstractFactoryContext<Converter<Object, ? extends A>>
        implements ConverterFactoryContext<A> {

    protected final FactoryHelper factoryHelper;

    protected AbstractConverterFactoryContext(FactoryHelper factoryHelper) {
        this.factoryHelper = factoryHelper;
    }

    @Nonnull
    @Override
    public abstract ImmutableList<? extends Factory<Converter<Object, ? extends A>>> getFactoriesInternal();

    @Nonnull
    public A convert(@Nonnull Object object) {
        return createFromInstance(object).convert(object);
    }
}
