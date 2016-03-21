package org.slieb.soy.factories.soydata;


import org.slieb.soy.internal.Converter;
import com.google.inject.Singleton;
import com.google.template.soy.data.SoyData;
import org.slieb.soy.context.SoyDataFactoryContext;
import org.slieb.soy.converters.soydata.DynamicConverter;
import org.slieb.soy.converters.soydata.LazyMapDataConverter;
import org.slieb.soy.converters.soydata.SoyMapDataConverter;
import org.slieb.soy.factories.SoyConverterFactory;

import javax.annotation.Nonnull;
import java.util.Map;

import static org.slieb.soy.converters.soydata.NullSafeConverter.wrapConverterWithNullSafe;

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
