package slieb.soy.factories.internal;


import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFactoryContext<A> implements FactoryContext<A> {

    private final Map<Class<?>, A> cacheValueMap;

    protected AbstractFactoryContext() {
        cacheValueMap = new HashMap<>();
    }

    @Nonnull
    @Override
    public abstract ImmutableList<? extends Factory<A>> getFactories();

    @Nonnull
    @Override
    public Factory<A> getFactory(@Nonnull Class<?> classObject) {
        for (Factory<A> factory : getFactories()) {
            if (factory.canCreate(classObject)) {
                return factory;
            }
        }
        throw new RuntimeException();
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

    @Nonnull
    public A createFromInstance(@Nonnull Object instanceObject) {
        return create(instanceObject.getClass());
    }
}
