package slieb.utilities;


import ch.lambdaj.function.convert.Converter;

import javax.annotation.Nonnull;

import static com.google.common.collect.Lists.newArrayList;

public class ConverterUtils {
    @Nonnull
    public static ChainedConverter chain(@Nonnull Converter... converters) {
        return new ChainedConverter(newArrayList(converters));
    }

    @Nonnull
    public static <A, B> JoinedConverter<A, B> join(@Nonnull Converter<A, ?> converterA,
                                                    @Nonnull Converter<Object, ? extends B> converterB) {
        return new JoinedConverter<>(converterA, converterB);
    }
}
