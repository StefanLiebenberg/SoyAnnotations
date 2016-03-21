package slieb.soy.factories.soydata;

import com.google.template.soy.data.SoyValue;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.DynamicConverter;
import slieb.soy.converters.soydata.NullSafeConverter;
import slieb.soy.converters.soydata.SoyListDataConverter;
import slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;
import java.util.function.Function;

import static slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;

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
