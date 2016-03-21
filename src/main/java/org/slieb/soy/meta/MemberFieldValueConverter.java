package org.slieb.soy.meta;

import java.lang.reflect.Field;
import java.util.function.Function;

public class MemberFieldValueConverter implements Function<Object, Object> {

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
