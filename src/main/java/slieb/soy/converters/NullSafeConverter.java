package slieb.soy.converters;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import com.google.template.soy.data.restricted.NullData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NullSafeConverter implements Converter<Object, SoyData> {

    private final Converter<Object, ? extends SoyData> converter;

    public NullSafeConverter(@Nonnull Converter<Object, ? extends SoyData> converter) {
        this.converter = converter;
    }

    @Nonnull
    private SoyData convertResult(@Nullable SoyData from) {
        return from != null ? from : NullData.INSTANCE;
    }

    @Override
    @Nonnull
    public SoyData convert(@Nullable Object from) {
        return convertResult(converter.convert(from));
    }

    @Nonnull
    public static NullSafeConverter wrapConverterWithNullSafe(
            @Nonnull Converter<Object, ? extends SoyData> converter) {
        return new NullSafeConverter(converter);
    }


}
