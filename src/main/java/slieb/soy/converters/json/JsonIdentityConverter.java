package slieb.soy.converters.json;


import ch.lambdaj.function.convert.Converter;

public class JsonIdentityConverter implements Converter<Object, Object> {

    public static final JsonIdentityConverter INSTANCE = new JsonIdentityConverter();

    private JsonIdentityConverter() {}

    @Override
    public Object convert(Object from) {
        return from;
    }
}
