package slieb.soy.model;


import ch.lambdaj.function.convert.Converter;

import javax.annotation.Nonnull;

public class DefaultLazyResult<A, B> implements LazyResult<B> {

    protected final A object;

    protected final Converter<A, B> converter;

    private B result;

    public DefaultLazyResult(@Nonnull A object, @Nonnull Converter<A, B> converter) {
        this.object = object;
        this.converter = converter;
    }

    @Nonnull
    public B result() {
        if (result == null) {
            result = converter.convert(object);
        }
        return result;
    }

    @Override
    public String toString() {

        return result().toString();

    }
}
