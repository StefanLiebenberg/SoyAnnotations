package slieb.soy.context.internal;


import slieb.soy.exceptions.MissingFactory;
import slieb.soy.factories.Factory;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.collect.Lists.reverse;

public abstract class AbstractFactoryContext<A> {


    public abstract void addFactory(@Nonnull Factory<A> factory);

    @Nonnull

    public abstract List<Factory<A>> getFactories();

    @Nonnull
    public final Factory<A> getFactory(@Nonnull Class<?> classObject) throws MissingFactory {
        for (Factory<A> factory : reverse(getFactories())) {
            if (factory.canCreate(classObject)) {
                return factory;
            }
        }
        throw new MissingFactory(classObject);
    }


    @Nonnull
    public A createInternal(@Nonnull Class<?> classObject) {
        return getFactory(classObject).create(classObject);
    }

    @Nonnull

    public abstract A create(@Nonnull Class<?> classObject);

    @Nonnull

    public A createFromInstance(@Nonnull Object instanceObject) {
        return create(instanceObject.getClass());
    }

    @Nonnull
    public Boolean hasFactory(@Nonnull Class<?> classObject) {
        for (Factory<A> factory : reverse(getFactories())) {
            if (factory.canCreate(classObject)) {
                return true;
            }
        }
        return false;
    }
}
