package slieb.soy.factories.soydata;


import ch.lambdaj.function.convert.Converter;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;
import slieb.soy.context.SoyDataFactoryContext;
import slieb.soy.converters.soydata.DynamicConverter;
import slieb.soy.converters.soydata.LazyMapDataConverter;
import slieb.soy.converters.soydata.SoyMapDataConverter;
import slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;
import java.util.Map;

import static slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;

@Singleton
public class LazySoyMapDataConverterFactory implements SoyConverterFactory {

    @Nonnull
    @Override
    public Boolean canCreate(@Nonnull Class<?> classObject) {
        return Map.class.isAssignableFrom(classObject);
    }

    @Nonnull
    @Override
    public Converter<Object, ? extends SoyData> create(@Nonnull Class<?> classObject, @Nonnull SoyDataFactoryContext context) {
        return new LazyMapDataConverter(new SoyMapDataConverter(wrapConverterWithNullSafe(new DynamicConverter(context))));
    }
}
