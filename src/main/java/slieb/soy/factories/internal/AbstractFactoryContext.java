package slieb.soy.factories.internal;


import com.google.common.collect.ImmutableList;
import slieb.soy.exceptions.MissingFactory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFactoryContext<A> implements FactoryContext<A> {

    private final Map<Class<?>, A> cacheValueMap;

    protected AbstractFactoryContext() {
        cacheValueMap = new HashMap<>();
        customFactories = new ArrayList<>();
    }

    protected ImmutableList<? extends Factory<A>> factories;

    protected List<Factory<A>> customFactories;

    @Nonnull
    public abstract ImmutableList<? extends Factory<A>> getFactoriesInternal();


    @Nonnull
    @Override
    public ImmutableList<? extends Factory<A>> getFactories() {
        if (factories == null) {
            factories = getFactoriesInternal();
        }
        return factories;
    }

    @Nonnull
    @Override
    public Factory<A> getFactory(@Nonnull Class<?> classObject) {
        for (Factory<A> factory : getFactories()) {
            if (factory.canCreate(classObject)) {
                return factory;
            }
        }
        throw new MissingFactory();
    }

    @Nonnull
    @Override
    public A create(@Nonnull Class<?> classObject) {
        if (cacheValueMap.containsKey(classObject)) {
            return cacheValueMap.get(classObject);
        } else {
            Factory<A> factory = getFactory(classObject);
            A value = factory.create(classObject);
            cacheValueMap.put(classObject, value);
            return value;
        }
    }

    @Override
    public void addCustomFactory(@Nonnull Factory<A> factory) {
        if (!customFactories.contains(factory)) {
            customFactories.add(factory);
        }
    }

    @Nonnull
    public A createFromInstance(@Nonnull Object instanceObject) {
        return create(instanceObject.getClass());
    }
}
