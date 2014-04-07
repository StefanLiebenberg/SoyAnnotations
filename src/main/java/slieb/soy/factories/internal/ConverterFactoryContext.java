package slieb.soy.factories.internal;


import ch.lambdaj.function.convert.Converter;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;

public interface ConverterFactoryContext<A> extends FactoryContext<Converter<Object, ? extends A>> {

    @Nonnull
    @Override
    public ImmutableList<Factory<Converter<Object, ? extends A>>> getFactories();

    @Nonnull
    public Factory<Converter<Object, ? extends A>> getFactory(@Nonnull Class<?> classObject);

    @Nonnull
    public Factory<Converter<Object, ? extends A>> getClassConverterFactory();

    @Nonnull
    public Factory<Converter<Object, ? extends A>> getCollectionConverterFactory();

    @Nonnull
    public Factory<Converter<Object, ? extends A>> getMapConverterFactory();

    @Nonnull
    public Factory<Converter<Object, ? extends A>> getNativeConverterFactory();

    @Nonnull
    @Override
    public Converter<Object, ? extends A> create(@Nonnull Class<?> classObject);

    @Nonnull
    public Converter<Object, ? extends A> createFromInstance(@Nonnull Object objectInstance);

    @Nonnull
    public A convert(@Nonnull Object instanceObject);

}
