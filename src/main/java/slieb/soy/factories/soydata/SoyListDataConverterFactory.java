package slieb.soy.factories.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.template.soy.data.SoyData;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.DynamicConverter;
import slieb.soy.converters.soydata.SoyListDataConverter;
import slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;

import static slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;

public class SoyListDataConverterFactory implements SoyConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Iterable.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Converter<Object, ? extends SoyData> create(@Nonnull Class<?> classObject, @Nonnull SoyDataFactoryContext context) {
        return new SoyListDataConverter(wrapConverterWithNullSafe(new DynamicConverter(context)));
    }
}
