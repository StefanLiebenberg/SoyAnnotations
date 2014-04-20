package slieb.soy.model;


import ch.lambdaj.function.convert.Converter;

import javax.annotation.Nonnull;

public class DefaultLazyResultWithCustomToString<A, B> extends DefaultLazyResult<A, B> {
    public DefaultLazyResultWithCustomToString(@Nonnull A object, @Nonnull Converter<A, B> converter) {
        super(object, converter);
    }

    @Override
    public String toString() {
        return object.toString();
    }
}
