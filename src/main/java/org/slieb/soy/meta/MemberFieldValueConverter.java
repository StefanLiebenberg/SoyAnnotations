package org.slieb.soy.meta;


import org.slieb.soy.internal.Converter;

import java.lang.reflect.Field;

public class MemberFieldValueConverter implements Converter<Object, Object> {

    private final Field field;

    public MemberFieldValueConverter(Field field) {
        this.field = field;
    }

    @Override
    public Object apply(Object from) {
        try {
            return field.get(from);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
