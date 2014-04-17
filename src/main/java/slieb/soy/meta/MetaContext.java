package slieb.soy.meta;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Inject;
import slieb.soy.exceptions.MissingFactory;
import slieb.soy.factories.MetaConverterFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;

public class MetaContext implements Converter<Class<?>, MetaClassInformation> {


    private final List<MetaConverterFactory> factories;

    @Inject
    public MetaContext(Set<MetaConverterFactory> factories) {
        this.factories = newArrayList(factories);
    }


    public MetaConverterFactory getFactory(Class<?> classObject) {
        for (MetaConverterFactory factory : reverse(factories)) {
            if (factory.canCreate(classObject)) {
                return factory;
            }
        }
        throw new MissingFactory(classObject);
    }

    public Boolean hasFactory(Class<?> classObject) {
        for (MetaConverterFactory factory : reverse(factories)) {
            if (factory.canCreate(classObject)) {
                return true;
            }
        }
        return false;
    }


    @Nonnull
    public Converter<Class<?>, MetaClassInformation> create(@Nonnull Class<?> classObject) {
        return getFactory(classObject).create(classObject);
    }

    @Override
    public MetaClassInformation convert(Class<?> from) {
        return this.create(from).convert(from);
    }
}
