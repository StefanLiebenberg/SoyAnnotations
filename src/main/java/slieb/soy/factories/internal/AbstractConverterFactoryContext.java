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

    private ImmutableList<Factory<Converter<Object, ? extends A>>> converterFactories;

    @Nonnull
    @Override
    public ImmutableList<Factory<Converter<Object, ? extends A>>> getFactories() {
        if (converterFactories == null) {
            converterFactories =
                    new ImmutableList.Builder<Factory<Converter<Object, ? extends A>>>()
                            .add(getClassConverterFactory())
                            .add(getCollectionConverterFactory())
                            .add(getMapConverterFactory())
                            .add(getNativeConverterFactory())
                            .build();
        }
        return converterFactories;
    }


    @Nonnull
    public A convert(@Nonnull Object object) {
        return createFromInstance(object).convert(object);
    }


}
