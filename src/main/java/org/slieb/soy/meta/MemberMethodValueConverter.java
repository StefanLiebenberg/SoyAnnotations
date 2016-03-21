package org.slieb.soy.meta;


import org.slieb.soy.internal.Converter;

import java.lang.reflect.Method;

public class MemberMethodValueConverter implements Converter<Object, Object> {
    private final Method method;

    public MemberMethodValueConverter(Method method) {
        this.method = method;
    }

    @Override
    public Object apply(Object from) {
        try {
            return method.invoke(from);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
