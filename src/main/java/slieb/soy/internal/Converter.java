package slieb.soy.internal;

import java.util.function.Function;

@Deprecated
public interface Converter<A, B> extends
        Function<A, B>,
        ch.lambdaj.function.convert.Converter<A, B> {

    @Override
    B apply(A from);

    @Deprecated
    @Override
    default B convert(A a) {
        return apply(a);
    }
}
