package slieb.soy.factories.internal;


import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;

public interface FactoryContext<A> {

    @Nonnull
    public ImmutableList<? extends Factory<? extends A>> getFactories();

    @Nonnull
    public Factory<? extends A> getFactory(@Nonnull Class<?> classObject);

    @Nonnull
    public A create(@Nonnull Class<?> classObject);

    @Nonnull
    public A createFromInstance(@Nonnull Object instanceObject);
}
