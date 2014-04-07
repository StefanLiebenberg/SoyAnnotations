package slieb.soy.converters;

import ch.lambdaj.function.convert.Converter;
import slieb.soy.factories.internal.ConverterFactoryContext;

public class DynamicConverter<A> implements Converter<Object, A> {

    private final ConverterFactoryContext<A> factoryContext;

    public DynamicConverter(ConverterFactoryContext<A> factoryContext) {
        this.factoryContext = factoryContext;
    }

    @Override
    public A convert(Object o) {
        if (o != null) {
            return this.factoryContext.convert(o);
        } else {
            return null;
        }
    }
}

