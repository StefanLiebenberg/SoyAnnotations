package slieb.soy.context;

import ch.lambdaj.function.convert.Converter;
import com.google.inject.Inject;
import slieb.soy.exceptions.MissingFactory;
import slieb.soy.factories.JsonConverterFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;

public class JsonDataFactoryContext implements Converter<Object, Object> {

    private final List<JsonConverterFactory> factories;

    @Inject
    public JsonDataFactoryContext(Set<JsonConverterFactory> factories) {
        this.factories = newArrayList(factories);
    }

    @Nonnull
    public List<JsonConverterFactory> getFactories() {
        return factories;
    }

    @Nonnull
    public JsonConverterFactory getFactory(@Nonnull Class<?> classObject) {
        for (JsonConverterFactory factory : reverse(factories)) {
            if (factory.canCreate(classObject)) {
                return factory;
            }
        }
        throw new MissingFactory(classObject);
    }

    @Nonnull
    public Converter<Object, ?> create(@Nonnull Class<?> classObject) {
        return getFactory(classObject).create(classObject, this);
    }

    @Nonnull
    public Converter<Object, ?> createFromInstance(@Nonnull Object instanceObject) {
        return create(instanceObject.getClass());
    }

    @Nullable
    public Object convert(Object from) {
        if (from != null) {
            return createFromInstance(from).convert(from);
        } else {
            return null;
        }
    }
}
