package slieb.soy.converters.json;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Singleton;

@Singleton
public class NativeJsonConverter implements Converter<Object, Object> {
    @Override
    public Object convert(Object from) {
        return from;
    }
}
