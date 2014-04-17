package slieb.soy.meta;


import ch.lambdaj.function.convert.Converter;

import java.lang.reflect.Method;

public class MemberMethodValueConverter implements Converter<Object, Object> {
    private final Method method;

    public MemberMethodValueConverter(Method method) {
        this.method = method;
    }

    @Override
    public Object convert(Object from) {
        try {
            return method.invoke(from);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
