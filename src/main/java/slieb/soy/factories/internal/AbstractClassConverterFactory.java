package slieb.soy.factories.internal;


import ch.lambdaj.function.convert.Converter;
import com.google.common.collect.ImmutableMap;
import slieb.soy.converters.FieldConverter;
import slieb.soy.converters.MethodConverter;
import slieb.soy.helpers.FactoryHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class AbstractClassConverterFactory<A> extends AbstractConverterFactory<A> {

    protected AbstractClassConverterFactory(FactoryHelper factoryHelper, ConverterFactoryContext<A> factoryContext) {
        super(factoryHelper, factoryContext);
    }

    protected Converter<Object, ? extends A> getFieldConverter(Field field) {
        return new FieldConverter<>(field,
                factoryHelper.isDynamicFactoryField(field) || factoryHelper.isDynamicFactoryClass(field.getDeclaringClass()) ?
                        getDynamicConverter() :
                        getTypeConverter(field.getType()));
    }

    protected Converter<Object, ? extends A> getMethodConverter(Method method) {
        return new MethodConverter<>(method,
                factoryHelper.isDynamicFactoryMethod(method) || factoryHelper.isDynamicFactoryClass(method.getDeclaringClass()) ?
                        getDynamicConverter() :
                        getTypeConverter(method.getReturnType()));
    }

    public ImmutableMap<String, Converter<Object, ? extends A>> getMembersConverterMap(Class<?> classObject) {
        final ImmutableMap.Builder<String, Converter<Object, ? extends A>> mapBuilder = ImmutableMap.builder();
        for (Field field : classObject.getFields()) {
            if (factoryHelper.isFactoryField(field)) {
                mapBuilder.put(factoryHelper.getFieldKey(field), getFieldConverter(field));
            }
        }
        for (Method method : classObject.getMethods()) {
            if (factoryHelper.isFactoryMethod(method)) {
                mapBuilder.put(factoryHelper.getMethodKey(method), getMethodConverter(method));
            }
        }
        return mapBuilder.build();
    }
}
