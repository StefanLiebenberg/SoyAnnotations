package slieb.soy.model;


import ch.lambdaj.function.convert.Converter;

import javax.annotation.Nonnull;

public class DefaultDynamicResult<A, B> implements DynamicResult<B> {

    private final A object;

    private final Converter<A, B> converter;

    private B result;

    public DefaultDynamicResult(@Nonnull A object, @Nonnull Converter<A, B> converter) {
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
}
