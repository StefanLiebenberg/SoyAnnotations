package slieb.soy.converters.soydata;

import com.google.template.soy.data.SoyValue;
import com.google.template.soy.data.restricted.NullData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public class NullSafeConverter implements Function<Object, SoyValue> {

    private final Function<Object, ? extends SoyValue> converter;

    public NullSafeConverter(@Nonnull Function<Object, ? extends SoyValue> converter) {
        this.converter = converter;
    }

    public Function<Object, ? extends SoyValue> getNestedConverter() {
        return converter;
    }

    @Override
    @Nonnull
    public SoyValue apply(@Nullable Object from) {
        if (from != null) {
            return convertResult(converter.apply(from));
        } else {
            return NullData.INSTANCE;
        }
    }

    @Nonnull
    private SoyValue convertResult(@Nullable SoyValue from) {
        return from != null ? from : NullData.INSTANCE;
    }

    @Nonnull
    public static NullSafeConverter wrapConverterWithNullSafe(@Nonnull Function<Object, ? extends SoyValue> converter) {
        return new NullSafeConverter(converter);
    }
}
