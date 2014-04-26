package slieb.soy.model;


import ch.lambdaj.function.convert.Converter;

import javax.annotation.Nonnull;

public class DefaultLazyResultWithOriginalToString<A, B> extends DefaultLazyResult<A, B> {

    public DefaultLazyResultWithOriginalToString(@Nonnull A object, @Nonnull Converter<A, B> converter) {
        super(object, converter);
    }

    @Override
    public String toString() {
        return object.toString();
    }
}
