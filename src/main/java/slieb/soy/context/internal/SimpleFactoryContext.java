package slieb.soy.context.internal;


import slieb.soy.factories.Factory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SimpleFactoryContext<A> extends AbstractFactoryContext<A> {

    protected final List<Factory<A>> factories = new ArrayList<>();

    @Override
    public final void addFactory(@Nonnull Factory<A> factory) {
        factories.add(factory);
    }

    @Nonnull
    @Override
    public List<Factory<A>> getFactories() {
        return factories;
    }

    @Nonnull
    @Override
    public A create(@Nonnull Class<?> classObject) {
        return createInternal(classObject);
    }
}
