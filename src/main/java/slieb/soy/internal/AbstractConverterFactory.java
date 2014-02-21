package slieb.soy.internal;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConverterFactory<A> {

    protected abstract boolean isClassConvertible(Class classObject);

    protected abstract boolean isFieldConvertible(Field field);

    protected abstract String getFieldKey(Field field);

    protected abstract boolean isMethodConvertible(Method method);

    protected abstract String getMethodKey(Method method);

    protected abstract Converter<Object, A>
    getFallbackConverter(Class classObject);

    protected abstract Converter<Map<String, A>, A>
    getMapConverter(Class classObject);


    protected Converter<Object, A> createFieldConverter(Field field) {
        Class type = field.getType();
        Converter<Object, A> typeConverter = create(type);
        return new FieldConverter<>(field, typeConverter);
    }

    private Converter<Object, A> createMethodConverter(Method method) {
        Class returnType = method.getReturnType();
        Converter<Object, A> typeConverter = create(returnType);
        return new MethodConverter<>(method, typeConverter);
    }

    private Converter<Object, A> createClassConverter(Class classObject) {
        Map<String, Converter<Object, A>> converterMap = new HashMap<>();
        for (Field field : classObject.getFields()) {
            if (isFieldConvertible(field)) {
                converterMap.put(getFieldKey(field),
                        createFieldConverter(field));
            }
        }

        for (Method method : classObject.getMethods()) {
            if (isMethodConvertible(method)) {
                converterMap.put(getMethodKey(method),
                        createMethodConverter(method));
            }
        }
        return new ClassConverter<>(converterMap, getMapConverter(classObject));
    }


    public Converter<Object, A> create(Class classObject) {
        if (isClassConvertible(classObject)) {
            return createClassConverter(classObject);
        } else {
            return getFallbackConverter(classObject);
        }
    }

}
