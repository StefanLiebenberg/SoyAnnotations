package slieb.soy.context;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Inject;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.data.restricted.NullData;
import slieb.soy.exceptions.MissingFactory;
import slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;


public class SoyDataFactoryContext implements Converter<Object, SoyData> {

    private final List<SoyConverterFactory> factories;

    @Inject
    public SoyDataFactoryContext(Set<SoyConverterFactory> factories) {
        this.factories = newArrayList(factories);
    }

    @Nonnull
    public List<SoyConverterFactory> getFactories() {
        return factories;
    }

    @Nonnull
    public SoyConverterFactory getFactory(@Nonnull Class<?> classObject) {
        for (SoyConverterFactory factory : reverse(factories)) {
            if (factory.canCreate(classObject)) {
                return factory;
            }
        }
        throw new MissingFactory(classObject);
    }

    @Nonnull
    public Converter<Object, ? extends SoyData> create(@Nonnull Class<?> classObject) {
        return getFactory(classObject).create(classObject, this);
    }

    @Nonnull
    public Converter<Object, ? extends SoyData> createFromInstance(@Nonnull Object instanceObject) {
        return create(instanceObject.getClass());
    }


    @Override
    @Nonnull
    public SoyData convert(Object from) {
        if (from != null) {
            return createFromInstance(from).convert(from);
        } else {
            return NullData.INSTANCE;
        }
    }


    public SoyData getSoyData(Object instanceObject) {
        return convert(instanceObject);
    }

    public SoyMapData getSoyMapData(Object instanceObject) {
        SoyData result = getSoyData(instanceObject);
        if (result instanceof SoyMapData) {
            return (SoyMapData) result;
        } else {
            return null;
        }
    }

}
