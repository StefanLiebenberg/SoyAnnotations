package slieb.utilities;

import ch.lambdaj.function.convert.Converter;


public class JoinedConverter<A, B> implements Converter<A, B> {

    private final Converter<A, ?> converterA;
    private final Converter<Object, ? extends B> converterB;

    public JoinedConverter(Converter<A, ?> converterA, Converter<Object, ? extends B> converterB) {
        this.converterA = converterA;
        this.converterB = converterB;
    }

    @Override
    public B convert(A from) {
        return converterB.convert(converterA.convert(from));
    }
}
