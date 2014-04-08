package slieb.soy.factories.internal;


import ch.lambdaj.function.convert.Converter;

import javax.annotation.Nonnull;

public interface ConverterFactoryContext<A> extends FactoryContext<Converter<Object, ? extends A>> {

    @Nonnull
    public Factory<Converter<Object, ? extends A>> getClassConverterFactory();

    @Nonnull
    public Factory<Converter<Object, ? extends A>> getCollectionConverterFactory();

    @Nonnull
    public Factory<Converter<Object, ? extends A>> getMapConverterFactory();

    @Nonnull
    public Factory<Converter<Object, ? extends A>> getNativeConverterFactory();


    @Nonnull
    public A convert(@Nonnull Object instanceObject);

}
