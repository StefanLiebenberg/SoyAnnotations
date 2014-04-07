package slieb.soy.converters;


import ch.lambdaj.function.convert.Converter;

public class ClassConverter<A> implements Converter<Object, A> {

    private final Class<?> classObject;

    private final Converter<Object, ? extends A> strictClassConverter;

    public ClassConverter(Class<?> classObject, Converter<Object, ? extends A> strictClassConverter) {
        this.classObject = classObject;
        this.strictClassConverter = strictClassConverter;
    }

    @Override
    public A convert(Object from) {
        if (from != null && classObject.isAssignableFrom(from.getClass())) {
            return strictClassConverter.convert(from);
        } else {
            return null;
        }
    }
}
