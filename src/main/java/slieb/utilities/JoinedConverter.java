package slieb.utilities;

import java.util.function.Function;

public class JoinedConverter<A, B> implements Function<A, B> {

    private final Function<A, ?> converterA;
    private final Function<Object, ? extends B> converterB;

    public JoinedConverter(Function<A, ?> converterA,
                           Function<Object, ? extends B> converterB) {
        this.converterA = converterA;
        this.converterB = converterB;
    }

    @Override
    public B apply(A from) {
        return converterB.apply(converterA.apply(from));
    }
}
