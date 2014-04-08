package slieb.soy.factories.json;


import ch.lambdaj.function.convert.Converter;
import slieb.soy.converters.ClassConverter;
import slieb.soy.converters.json.StrictClassToJsonMapConverter;
import slieb.soy.factories.internal.AbstractClassConverterFactory;
import slieb.soy.factories.internal.ConverterFactoryContext;
import slieb.soy.helpers.FactoryHelper;

import javax.annotation.Nonnull;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class JsonClassConverterFactory extends AbstractClassConverterFactory<Object> {

    public JsonClassConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<Object> factoryContext) {
        super(factoryHelper, factoryContext);
    }

    private Converter<Object, Map<String, Object>> createClass(@Nonnull Class<?> classObject) {
        return new ClassConverter<>(classObject, new StrictClassToJsonMapConverter(getMembersConverterMap(classObject)));
    }

    @Nonnull
    @Override
    public Converter<Object, Map<String, Object>> create(@Nonnull Class<?> classObject) {
        checkArgument(canCreate(classObject));
        if (canCreateSelf(classObject)) {
            return createClass(classObject);
        } else {
            return create(classObject.getSuperclass());
        }
    }

    private Boolean canCreateFromSuperClass(Class<?> classObject) {
        final Class<?> superClassObject = classObject.getSuperclass();
        return superClassObject != null && !superClassObject.isPrimitive() &&
                !superClassObject.equals(Object.class) &&
                canCreate(superClassObject);
    }

    private Boolean canCreateSelf(Class<?> classObject) {
        return factoryHelper.isFactoryClass(classObject);
    }

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return canCreateSelf(classObject) || canCreateFromSuperClass(classObject);
    }
}
