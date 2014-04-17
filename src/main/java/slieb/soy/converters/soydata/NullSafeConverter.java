package slieb.soy.converters.soydata;


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

    public Converter<Object, ? extends SoyData> getNestedConverter() {
        return converter;
    }

    @Override
    @Nonnull
    public SoyData convert(@Nullable Object from) {
        if (from != null) {
            return convertResult(converter.convert(from));
        } else {
            return NullData.INSTANCE;
        }
    }


    @Nonnull
    private SoyData convertResult(@Nullable SoyData from) {
        return from != null ? from : NullData.INSTANCE;
    }

    @Nonnull
    public static NullSafeConverter wrapConverterWithNullSafe(@Nonnull Converter<Object, ? extends SoyData> converter) {
        return new NullSafeConverter(converter);
    }


}
