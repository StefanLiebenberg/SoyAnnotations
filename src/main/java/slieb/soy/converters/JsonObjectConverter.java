package slieb.soy.converters;


import ch.lambdaj.function.convert.Converter;

public class JsonObjectConverter implements Converter<Object, Object> {
    @Override
    public Object convert(Object from) {
        return from;
    }
}
