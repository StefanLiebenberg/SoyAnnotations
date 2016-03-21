package org.slieb.utilities;

import javax.annotation.Nonnull;
import java.util.function.Function;

import static com.google.common.collect.Lists.newArrayList;

public class ConverterUtils {

    @Nonnull
    public static ChainedConverter chain(@Nonnull Function... converters) {
        return new ChainedConverter(newArrayList(converters));
    }

    @Nonnull
    public static <A, B> JoinedConverter<A, B> join(@Nonnull Function<A, ?> converterA,
                                                    @Nonnull Function<Object, ? extends B> converterB) {
        return new JoinedConverter<>(converterA, converterB);
    }
}
