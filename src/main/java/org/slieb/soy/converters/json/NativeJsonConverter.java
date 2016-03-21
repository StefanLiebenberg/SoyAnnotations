package org.slieb.soy.converters.json;


import org.slieb.soy.internal.Converter;
import com.google.inject.Singleton;

@Singleton
public class NativeJsonConverter implements Converter<Object, Object> {
    @Override
    public Object apply(Object from) {
        return from;
    }
}
