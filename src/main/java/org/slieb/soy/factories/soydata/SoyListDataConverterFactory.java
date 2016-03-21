package org.slieb.soy.factories.soydata;

import com.google.template.soy.data.SoyValue;
import org.slieb.soy.context.SoyDataFactoryContext;
import org.slieb.soy.converters.soydata.DynamicConverter;
import org.slieb.soy.converters.soydata.NullSafeConverter;
import org.slieb.soy.converters.soydata.SoyListDataConverter;
import org.slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;
import java.util.function.Function;

import static org.slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;

public class SoyListDataConverterFactory implements SoyConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Iterable.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Function<Object, ? extends SoyValue> create(@Nonnull Class<?> classObject,
                                                       @Nonnull SoyDataFactoryContext context) {
        final NullSafeConverter itemConverter = wrapConverterWithNullSafe(new DynamicConverter(context));
        return new SoyListDataConverter(itemConverter);
    }
}
