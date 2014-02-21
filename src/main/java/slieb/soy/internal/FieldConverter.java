package slieb.soy.internal;

import ch.lambdaj.function.convert.Converter;

import java.lang.reflect.Field;

public class FieldConverter<A>
        implements Converter<Object, A> {

    private final Field field;

    private final Converter<Object, A> typeConverter;

    public FieldConverter(Field field, Converter<Object, A> typeConverter) {
        this.field = field;
        this.typeConverter = typeConverter;
    }

    @Override
    public A convert(Object object) {
        try {
            return typeConverter.convert(field.get(object));
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }
}
